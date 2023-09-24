package cn.anton.serviceprice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/*
 * @author: Anton
 * @create_date: 2023/9/24 15:52
 */
@Configuration
public class ThreadConfig {
	
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
