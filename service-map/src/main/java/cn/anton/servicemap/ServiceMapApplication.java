package cn.anton.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMapApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceMapApplication.class, args);
	}
	
}
