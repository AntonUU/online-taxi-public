package cn.anton.serviceprice.service;

import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/6 21:15
 */
public interface PriceRuleService extends IService<PriceRule> {
	ResponseResult add(PriceRule priceRule, String type);
	
	List<PriceRule> getNewestVersion(String fareType);
	
	List<PriceRule> getPriceRuleToDesc(String cityCode, String vehicleType);
	
	ResponseResult isNew(String fareType, Integer fareVersion);
	
	ResponseResult ifExists(String cityCode, String vehicleType);
}
