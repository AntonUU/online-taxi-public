package cn.anton.apipassenger.config;

import cn.anton.apipassenger.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @author: Anton
 * @create_date: 2023/9/7 21:30
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	/**
	 * 先初始化Bean再初始化拦截器
	 * @return
	 */
	@Bean
	public JWTInterceptor jwtInterceptor(){
		return new JWTInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
				.addPathPatterns("/**")  //拦截的路径
				.excludePathPatterns("/verification-code**"); // 不拦截的路径
	}
}
