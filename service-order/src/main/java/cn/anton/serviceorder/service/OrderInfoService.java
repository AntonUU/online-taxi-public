package cn.anton.serviceorder.service;

import cn.anton.internalcommon.dao.OrderInfo;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-06
 */
public interface OrderInfoService extends IService<OrderInfo> {

	public ResponseResult add(OrderRequest orderRequest);
	
	/**
	 * 派单
	 * @param orderInfo
	 */
	void dispatchRealTimeOrder(OrderInfo orderInfo);
	
}
