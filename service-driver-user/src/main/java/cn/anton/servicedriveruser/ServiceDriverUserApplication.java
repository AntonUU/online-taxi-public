package cn.anton.servicedriveruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * @author: Anton
 * @create_date: 2023/9/25 10:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceDriverUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceDriverUserApplication.class, args);
	}

}
