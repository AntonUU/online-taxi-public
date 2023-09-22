package cn.anton.serviceprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * @author: Anton
 * @create_date: 2023/9/22 19:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServicePriceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServicePriceApplication.class, args);
	}
	
}
