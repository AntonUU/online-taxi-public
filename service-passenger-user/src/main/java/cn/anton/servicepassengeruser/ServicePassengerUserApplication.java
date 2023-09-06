package cn.anton.servicepassengeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * @author: Anton
 * @create_date: 2023/9/6 18:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServicePassengerUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServicePassengerUserApplication.class);
	}
	
}
