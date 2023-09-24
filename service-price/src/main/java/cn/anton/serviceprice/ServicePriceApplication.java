package cn.anton.serviceprice;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * 计价服务
 * @author: Anton
 * @create_date: 2023/9/22 19:29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.anton.serviceprice.mapper")
public class ServicePriceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServicePriceApplication.class, args);
	}
	
}
