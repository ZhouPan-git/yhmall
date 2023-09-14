package com.yhmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nick
 * @Classname SecurityApplication
 * @Date 2023/09/14 10:26
 * @Description
 */
@SpringBootApplication
@MapperScan("com.yhmall.dao")
@EnableFeignClients(basePackages = {"com.yhmall.api"})
@RefreshScope
@EnableDiscoveryClient
public class SecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}
