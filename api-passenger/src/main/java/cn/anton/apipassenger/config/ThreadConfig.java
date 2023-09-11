package cn.anton.apipassenger.config;

import cn.anton.internalcommon.dao.PassengerUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author: Anton
 * @create_date: 2023/9/11 18:35
 */
@Configuration
public class ThreadConfig {
	
	/**
	 * 本地线程传输
	 *
	 * @return
	 */
	@Bean("passengerUserThreadLocal")
	public ThreadLocal<PassengerUser> passengerUserThreadLocal() {
		return new ThreadLocal<PassengerUser>();
	}
	
}
