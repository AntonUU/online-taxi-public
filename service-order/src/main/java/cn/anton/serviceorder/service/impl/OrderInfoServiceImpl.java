package cn.anton.serviceorder.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.OrderConstant;
import cn.anton.internalcommon.dao.OrderInfo;
import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import cn.anton.serviceorder.mapper.OrderInfoMapper;
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
	
	
	@Override
	public ResponseResult add(OrderRequest orderRequest) {
		// 判断下单账号是否是黑名单
		String deviceCode = orderRequest.getDeviceCode();
		String deviceCodeKey = PrefixGeneratorUtils.generatorBlackDeviceCodeKey(orderRequest.getDeviceCode());
		if (isBlacklist(deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.ORDER_FORBID_PLACE.getCode(), CommonStatusEnum.ORDER_FORBID_PLACE.getMessage());
		
		// 判断下单的城市和计价规则是否正常
		if (ifExistsCity(orderRequest))
			return ResponseResult.fail(CommonStatusEnum.CITY_SREVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SREVICE_NOT_SERVICE.getMessage());
		
		// 判断计价规则的版本是是否为最新
		if (ifisNew(orderRequest, deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_THERE_ARE_CHANGES.getCode(), CommonStatusEnum.PRICE_RULE_THERE_ARE_CHANGES.getMessage());
		
		// 判断当前有订单尚未完成
		if (isNotCompleted(orderRequest, deviceCodeKey))
			return ResponseResult.fail(CommonStatusEnum.ORDER_NOT_COMPLETED.getCode(), CommonStatusEnum.ORDER_NOT_COMPLETED.getMessage());
		
		// 创建订单
		OrderInfo orderInfo = gengerateOrderInfo(orderRequest);
		save(orderInfo);
		
		return ResponseResult.success();
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
		int count = getNotCompletedCount(orderRequest);
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
	
	private int getNotCompletedCount(OrderRequest orderRequest) {
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
}
