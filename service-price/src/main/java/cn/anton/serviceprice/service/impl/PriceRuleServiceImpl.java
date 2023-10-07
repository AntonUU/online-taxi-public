package cn.anton.serviceprice.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.serviceprice.mapper.PriceRuleMapper;
import cn.anton.serviceprice.service.PriceRuleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/6 21:15
 */
@Service
@Slf4j
public class PriceRuleServiceImpl extends ServiceImpl<PriceRuleMapper, PriceRule> implements PriceRuleService {
	
	public static final String DESC_FARE_VERSION = "fare_version";
	
	@Override
	public ResponseResult add(PriceRule priceRule, String type) {
		if (!"add".equals(type))
			if (!"update".equals(type))
				return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_TYPE_ERROR.getCode(), CommonStatusEnum.PRICE_RULE_TYPE_ERROR.getMessage());
		
		String cityCode = priceRule.getCityCode();
		String vehicleType = priceRule.getVehicleType();
		
		// 查询有无该计价规则， 有更新版本号  无插入计价规则
		
		// 如果存在多条记录  取最大的版本
		List<PriceRule> priceRules = getPriceRuleToDesc(cityCode, vehicleType);;
		Integer fareVersion = 0;
		if (priceRules.size() > 0) {
			// 不允许重复添加规则
			if ("add".equals(type)) {
				return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(), CommonStatusEnum.PRICE_RULE_EXISTS.getMessage());
			}
			// 更新不允许参数 一致
			PriceRule price = priceRules.get(0);
			if (price.getStartFare().equals(priceRule.getStartFare()) &&
					    price.getStartMile().equals(priceRule.getStartMile()) &&
			price.getUnitPricePerMile().equals(priceRule.getUnitPricePerMile()) &&
			price.getUnitPricePerMinute().equals(priceRule.getUnitPricePerMinute())) {
				return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_UNCHANGED.getCode(), CommonStatusEnum.PRICE_RULE_UNCHANGED.getMessage());
			}
			
			fareVersion = price.getFareVersion();
		}
		
		// 设置FareType  cityCode&vehicleType
		priceRule.setFareType(cityCode + "$" + vehicleType);
		
		// 设置版本号
		priceRule.setFareVersion(++fareVersion);
		
		this.save(priceRule);
		
		return ResponseResult.success();
	}
	
	@Override
	public List<PriceRule> getPriceRuleToDesc(String cityCode, String vehicleType) {
		QueryWrapper<PriceRule> queryWrapper =
				new QueryWrapper<PriceRule>()
						.eq("city_code", cityCode)
						.eq("vehicle_type", vehicleType)
						.orderByDesc(DESC_FARE_VERSION);
		
		List<PriceRule> list = this.list(queryWrapper);
		log.info("当前查出的计价规则: cityCode: {}, vehicleType: {}, 结果: {}", cityCode, vehicleType, list);
		return this.list(queryWrapper);
	}
	
	@Override
	public List<PriceRule> getNewestVersion(String fareType) {
		String[] primary = fareType.split("\\$");
		if (primary.length != 2) {
			return null;
		}
		String cityCode = primary[0];
		String vehicleType = primary[1];
		
		return getPriceRuleToDesc(cityCode, vehicleType);
	}
	
	@Override
	public ResponseResult isNew(String fareType, Integer fareVersion) {
		List<PriceRule> newestVersion = getNewestVersion(fareType);
		/**
		 * 参数类型错误
		 */
		if (newestVersion == null) {
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_TYPE_ERROR.getCode(), CommonStatusEnum.PRICE_RULE_TYPE_ERROR.getMessage());
		}
		/**
		 * 计价规则不存在
		 */
		if (newestVersion.size() == 0) {
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
		}
		PriceRule priceRule = newestVersion.get(0);
		
		return ResponseResult.success(priceRule.getFareVersion().equals(fareVersion));
	}
	
	@Override
	public ResponseResult ifExists(String cityCode, String vehicleType) {
		
		int count = this.count(new QueryWrapper<PriceRule>()
				                       .eq("city_code", cityCode)
				                       .eq("vehicle_type", vehicleType));
		
		
		return ResponseResult.success(count > 0);
	}
}
