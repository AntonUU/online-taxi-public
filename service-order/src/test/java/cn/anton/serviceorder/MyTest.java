package cn.anton.serviceorder;

import cn.anton.internalcommon.dao.OrderInfo;
import cn.anton.serviceorder.service.OrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/9 10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

	@Resource
	private OrderInfoService orderInfoService;
	
	@Test
	public void dispatchRealTimeOrderTest(){
		OrderInfo orderInfo = new OrderInfo();
		
//		String depLatitude = orderInfo.getDepLatitude();
//		String depLongitude = orderInfo.getDepLongitude();
		orderInfo.setDeparture("22.801053");
		orderInfo.setDepLongitude("108.443802");
		
		orderInfoService.dispatchRealTimeOrder(orderInfo);
	}
	
}
