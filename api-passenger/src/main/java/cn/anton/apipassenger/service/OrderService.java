package cn.anton.apipassenger.service;

import cn.anton.apipassenger.feign.ServiceOrderClient;
import cn.anton.apipassenger.feign.ServicePassengerUserClient;
import cn.anton.internalcommon.dao.PassengerUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/6 13:33
 */
@Service
public class OrderService {
	
	@Resource
	private ServiceOrderClient serviceOrderClient;
	
	@Autowired
	@Qualifier("passengerUserThreadLocal")
	private ThreadLocal<PassengerUser> threadLocal;
	
	@Resource
	private ServicePassengerUserClient servicePassengerUserClient;
	
	public ResponseResult add(OrderRequest orderRequest) {
		PassengerUser passengerUser = threadLocal.get();
		String phone = passengerUser.getPassengerPhone();
		
		//去数据库查询UserId
		ResponseResult<PassengerUser> user = servicePassengerUserClient.getUser(phone);
		PassengerUser data = user.getData();
		Long passengerUserId = data.getId();
		
		// 设置乘客手机号与id
		orderRequest.setPassengerId(passengerUserId);
		orderRequest.setPassengerPhone(phone);
		
		return serviceOrderClient.add(orderRequest);
	}
	
}
