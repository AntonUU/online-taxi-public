package cn.anton.apipassenger.config;

import cn.anton.apipassenger.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @author: Anton
 * @create_date: 2023/9/7 21:30
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JWTInterceptor())
				.addPathPatterns("/**")  //拦截的路径
				.excludePathPatterns("/no-auth"); // 不拦截的路径
	}
}
