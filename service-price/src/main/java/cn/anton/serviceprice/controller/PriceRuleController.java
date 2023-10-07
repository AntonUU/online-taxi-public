package cn.anton.serviceprice.controller;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.serviceprice.service.PriceRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/6 21:15
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

	@Resource
	private PriceRuleService priceRuleService;
	
	/**
	 * 添加计价规则
	 * @return
	 */
	@RequestMapping("/process/{type}")
	public ResponseResult add(@RequestBody PriceRule priceRule, @PathVariable("type") String type){
		return priceRuleService.add(priceRule, type);
	}
	
	/**
	 * 查询最新城市车辆的计价规则
	 * @return
	 */
	@GetMapping("/get-newest-version")
	public ResponseResult getNewestVersion(@RequestParam("fareType") String fareType){
		List<PriceRule> newestVersion = priceRuleService.getNewestVersion(fareType);
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
		
		return ResponseResult.success(newestVersion.get(0));
	}
	
	@GetMapping("/is-new")
	public ResponseResult isNew(@RequestParam("fareType") String fareType, @RequestParam("fareVersion") Integer fareVersion){
		return priceRuleService.isNew(fareType, fareVersion);
	}
	
	@GetMapping("/if-exists")
	public ResponseResult ifExists(@RequestBody PriceRule priceRule){
		String cityCode = priceRule.getCityCode();
		String vehicleType = priceRule.getVehicleType();
		if (cityCode == null || vehicleType == null)
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), CommonStatusEnum.FAIL.getMessage());
		return priceRuleService.ifExists(cityCode, vehicleType);
	}
	
}
