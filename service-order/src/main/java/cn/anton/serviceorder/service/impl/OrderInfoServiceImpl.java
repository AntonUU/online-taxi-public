package cn.anton.serviceorder.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.OrderConstant;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.OrderInfo;
import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.AroundsearchRequest;
import cn.anton.internalcommon.request.OrderRequest;
import cn.anton.internalcommon.response.OrderDriverResponse;
import cn.anton.internalcommon.response.TerminalResponse;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import cn.anton.serviceorder.mapper.OrderInfoMapper;
import cn.anton.serviceorder.remote.ServiceDriverUserClient;
import cn.anton.serviceorder.remote.ServiceMapClient;
import cn.anton.serviceorder.remote.ServicePriceClient;
import cn.anton.serviceorder.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-06
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
	
	@Resource
	private ServicePriceClient servicePriceClient;
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Resource
	private ServiceMapClient serviceMapClient;
	
	
	/**
	 * 乘客下单
	 * @param orderRequest
	 * @return
	 */
	@Override
	public ResponseResult add(OrderRequest orderRequest) {
//		// 查看当前城市是否有可用司机
		if (!isAvailableDriver(orderRequest))
			return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(), CommonStatusEnum.CITY_DRIVER_EMPTY.getMessage());
//
//		// 判断下单账号是否是黑名单
		String deviceCodeKey = PrefixGeneratorUtils.generatorBlackDeviceCodeKey(orderRequest.getDeviceCode());
		if (isBlacklist(deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.ORDER_FORBID_PLACE.getCode(), CommonStatusEnum.ORDER_FORBID_PLACE.getMessage());
//
//		// 判断下单的城市和计价规则是否正常
		if (ifExistsCity(orderRequest))
			return ResponseResult.fail(CommonStatusEnum.CITY_SREVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SREVICE_NOT_SERVICE.getMessage());
//
//		// 判断计价规则的版本是是否为最新
		if (ifisNew(orderRequest, deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_THERE_ARE_CHANGES.getCode(), CommonStatusEnum.PRICE_RULE_THERE_ARE_CHANGES.getMessage());

//		// 判断用户当前有订单尚未完成
		if (isNotCompleted(orderRequest, deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.ORDER_NOT_COMPLETED.getCode(), CommonStatusEnum.ORDER_NOT_COMPLETED.getMessage());
//
//		// 创建订单
		OrderInfo orderInfo = gengerateOrderInfo(orderRequest);

		// 派单
		dispatchRealTimeOrder(orderInfo);
		
		// 将订单存入db
		save(orderInfo);
		
		return ResponseResult.success();
	}
	
	/**
	 * 实时订单派单逻辑
	 * @param orderInfo
	 */
	@Override
	public void dispatchRealTimeOrder(OrderInfo orderInfo){
		int radius = 2;
		List<TerminalResponse> terminals = null;
		// 2km
		String depLatitude = orderInfo.getDepLatitude();
		String depLongitude = orderInfo.getDepLongitude();
		// 中心点
		String center = depLatitude + "," + depLongitude;
		
		HashMap<String, TerminalResponse> findWorkCar = new HashMap<>();
		for (int i = 0; i < 18; i++) {
			int ans = (((i % 3) + 1) * radius * 1000);
			AroundsearchRequest aroundsearchRequest = new AroundsearchRequest();
			aroundsearchRequest.setCenter(center);
			aroundsearchRequest.setRadius(ans + "");
			ResponseResult<List<TerminalResponse>> terminalResult = serviceMapClient.aroundsearch(aroundsearchRequest);
			terminals = terminalResult.getData();
			log.info("当前搜索范围(单位: 米): {}, 当前位置司机数量: {}", ans,terminals);
			if (terminals != null || terminals.size() > 0) {
				
				// 解析数据
				for (int t = 0; t < terminals.size(); t++) {
					// 通过解析的数据, 查询db司机是否处于工作状态 ?
					TerminalResponse terminalResponse = terminals.get(t);
					String carId = terminalResponse.getCarId();
					// 通过carId获取正在工作的司机信息
					ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
					if (availableDriver.getData() == null) {
						continue;
					}
					OrderDriverResponse orderDriverResponse = availableDriver.getData();
					// 查看司机是否还有未完成的订单
					int driverNotCompleted = isDriverNotCompleted(orderDriverResponse.getDriverId());
					if (driverNotCompleted == 0) {
						// 司机处于等待派单状态  可以进行派单
						log.info("找到了司机: {}", orderDriverResponse);
						
						// 填充订单信息
						orderInfo.setDriverId(orderDriverResponse.getDriverId());
						orderInfo.setDriverPhone(orderDriverResponse.getDriverPhone());
						orderInfo.setCarId(orderDriverResponse.getCarId());
						orderInfo.setReceiveOrderCarLatitude(terminalResponse.getLatitude()); // 接单时车辆纬度
						orderInfo.setReceiveOrderCarLongitude(terminalResponse.getLongitude()); // 接单时车辆经度
						orderInfo.setReceiveOrderTime(LocalDateTime.now());
						orderInfo.setLicenseId(orderDriverResponse.getLicenseId());
						orderInfo.setVehicleNo(orderDriverResponse.getVehicleOn());
						orderInfo.setOrderStatus(OrderConstant.ORDER_STATUS_DRIVER_TAKE);
						log.info("获取到的经纬度: {}", terminalResponse.getLongitude() + terminalResponse.getLatitude());
						// 退出不再进行司机的查找
					}
//
				}
				// TODO 多车辆派单, 进行Redis锁!!!  未做
				// 找出合适车辆, 进行派单
				// 如果派单成功, 则退出循环
				log.info("找到可用车辆{}架: {}", findWorkCar.size(), findWorkCar);
				
				return;
			}
		}
		log.info("当前位置没有找到可用司机....");
		
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	
	private boolean isAvailableDriver(OrderRequest orderRequest) {
		Long address = orderRequest.getAddress();
		// 通过cityCode查看当前城市是否有司机可用
		ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(address + "");
		return availableDriver.getData();
	}
	
	private boolean ifExistsCity(OrderRequest orderRequest) {
		String fareType = orderRequest.getFareType();
		String[] split = fareType.split("\\$");
		PriceRule priceRule = new PriceRule();
		priceRule.setCityCode(split[0]);
		priceRule.setVehicleType(split[1]);
		ResponseResult<Boolean> ifExists = servicePriceClient.ifExists(priceRule);
		Boolean data = ifExists.getData();
		if (!data) {
			return true;
		}
		return false;
	}
	
	private boolean isNotCompleted(OrderRequest orderRequest, String deviceCodeKey) {
		int count = isPassengerNotCompleted(orderRequest);
		if (count > 0) {
			stringRedisTemplate.opsForValue().decrement(deviceCodeKey);
			return true;
		}
		return false;
	}
	
	private boolean ifisNew(OrderRequest orderRequest, String deviceCodeKey) {
		ResponseResult<Boolean> aNewResult = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
		Boolean aNew = aNewResult.getData();
		if (aNewResult.getCode() != 1 || !aNew) {
			// Redis数据回滚
			stringRedisTemplate.opsForValue().decrement(deviceCodeKey);
			return true;
		}
		return false;
	}
	
	private boolean isBlacklist(String deviceCodeKey) {
		Long increment = 0L;
		
		Boolean deviceCodeFlag = stringRedisTemplate.hasKey(deviceCodeKey);
		if (deviceCodeFlag == null || !deviceCodeFlag){
			stringRedisTemplate.opsForValue().set(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
		} else {
			increment = stringRedisTemplate.opsForValue().increment(deviceCodeKey);
			if (increment != null && increment > 2L) {
				return true;
			}
		}
		return false;
	}
	
	private static OrderInfo gengerateOrderInfo(OrderRequest orderRequest) {
		OrderInfo orderInfo = new OrderInfo();
		
		BeanUtils.copyProperties(orderRequest, orderInfo);
		orderInfo.setOrderStatus(OrderConstant.ORDER_STATUS_START);
		LocalDateTime now = LocalDateTime.now();
		orderInfo.setGmtCreate(now);
		orderInfo.setGmtModified(now);
		return orderInfo;
	}
	
	/**
	 * 判断乘客是否还有未完成订单
	 * @param orderRequest
	 * @return
	 */
	private int isPassengerNotCompleted(OrderRequest orderRequest) {
		// 判断正在进行的订单不允许下单 1~7
		QueryWrapper<OrderInfo> infoQueryWrapper = new QueryWrapper<>();
		infoQueryWrapper.eq("passenger_id", orderRequest.getPassengerId()).and(f -> {
			f.eq("order_status", OrderConstant.ORDER_STATUS_START).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_DRIVER_TAKE).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_PICKUP).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_ARRIVED_POINT).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_GETIN).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_ARRIVED_DESTINATION).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_COLLECTION);
		});
		
		
		return this.count(infoQueryWrapper);
	}
	
	/**
	 * 判断司机是否有未完成订单
	 * @param driverId 司机id
	 * @return
	 */
	private int isDriverNotCompleted(Long driverId) {
		// 判断正在进行的订单不允许下单 1~7
		QueryWrapper<OrderInfo> infoQueryWrapper = new QueryWrapper<>();
		infoQueryWrapper.eq("driver_id", driverId).and(f -> {
			f.eq("order_status", OrderConstant.ORDER_STATUS_DRIVER_TAKE).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_PICKUP).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_ARRIVED_POINT).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_GETIN).or()
					.eq("order_status", OrderConstant.ORDER_STATUS_ARRIVED_DESTINATION);
		});
		
		return this.count(infoQueryWrapper);
	}
	
}
