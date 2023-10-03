package cn.anton.apipassenger.config;

import cn.anton.internalcommon.dao.PassengerUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
	
	/**
	 * 异步工作线程
	 */
	@Bean("executor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.setThreadNamePrefix("remote_task_executor_pool");
		executor.initialize();
		return executor;
	}
	
	
}
