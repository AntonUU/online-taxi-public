package cn.anton.serviceprice.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.DirectionResponse;
import cn.anton.internalcommon.response.ForecastPriceResponse;
import cn.anton.serviceprice.mapper.PriceRuleMapper;
import cn.anton.serviceprice.remote.ServiceMapClient;
import cn.anton.serviceprice.service.ForecastPriceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/*
 * @author: Anton
 * @create_date: 2023/9/22 19:35
 */
@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {
	
	@Resource
	private ServiceMapClient serviceMapClient;
	
	@Resource
	private PriceRuleMapper priceRuleMapper;
	
	@Resource
	private Executor executor;
	
	@SneakyThrows
	@Override
	public ResponseResult forecastPrice(ForecastPriceDTO dto) {
		ForecastPriceResponse response = new ForecastPriceResponse();
		DirectionResponse direction = null;
		List<PriceRule> priceRules = null;
		
		CompletableFuture<DirectionResponse> mapTaskExecutor = CompletableFuture.supplyAsync(() -> {
			log.info("调用地图服务-查询距离和市场: service-map");
			ResponseResult<DirectionResponse> driving = serviceMapClient.driving(dto);
			return driving.getData();
		}, executor);
		
		
		CompletableFuture<List<PriceRule>> priceRuleTaskExecutor = CompletableFuture.supplyAsync(() -> {
			log.info("读取计价规则");
			Map<String, Object> map = new HashMap<>();
			map.put("city_code", "11000");
			map.put("vehicle_type", "1");
			return priceRuleMapper.selectByMap(map);
		}, executor);
		
		CompletableFuture<Integer> combineAsync = mapTaskExecutor.thenCombineAsync(priceRuleTaskExecutor, (f1, f2) -> {
			if (f2.size() != 1 || f1 == null) return -1;
			return 1;
		});
		
		Integer combine = combineAsync.get();
		if (combine == -1)
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
		
		direction = mapTaskExecutor.get();
		priceRules = priceRuleTaskExecutor.get();
		
		if (priceRules.size() != 1)
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
		
		log.info("距离: {}, 时长: {}", direction.getDistance(), direction.getDuration());
		log.info("计价规则: {}", priceRules.get(0));
		log.info("根据距离，时长和计价规则，计算价格");
		
		Double price = getPrice(direction, priceRules.get(0));
		response.setPrice(price);
		return ResponseResult.success(response);
	}
	
	/**
	 * 根据距离、时长、和计价规则，计算最终价格
	 * @param direction 距离、时长
	 * @param priceRule 计价规则
	 * @return
	 */
	private Double getPrice(DirectionResponse direction, PriceRule priceRule){
		BigDecimal price = new BigDecimal("0");
		// 起步价
		BigDecimal startFareDecimal = new BigDecimal(priceRule.getStartFare());
		price = price.add(startFareDecimal);
		
		// 里程费
		// 总里程  单位: 米
		BigDecimal distanceDecimal = new BigDecimal(direction.getDistance());
		// 总里程 单位: 公里
		BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_UP);
		// 起步里程
		BigDecimal startMileDecimal = new BigDecimal(priceRule.getStartMile());
		// 总里程 - 起步里程
		Double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
		// 最终收费的里程 /KM
		Double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
		BigDecimal mileDecimal = new BigDecimal(mile);
		
		// 计程单价 单位: 元/KM
		Double unitPricePerMile = priceRule.getUnitPricePerMile();
		BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
		
		// 里程价格
		BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
		price = price.add(mileFare);
		
		// 时长费
		// 时长  单位: 秒
		BigDecimal time = new BigDecimal(direction.getDuration());
		// 时长  单位: 分
		BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
		
		// 计时单价
		Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
		BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
		
		// 时长总费用
		BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);
		price = price.add(timeFare);
		
		return price.doubleValue();
	}
}
