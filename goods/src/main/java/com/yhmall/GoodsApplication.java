package com.yhmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Nick
 * @Classname GoodsApplication
 * @Date 2023/09/14 10:42
 * @Description
 */
@SpringBootApplication
@MapperScan("com.yhmall.dao")
@EnableFeignClients(basePackages = {"com.yhmall.api"})
@RefreshScope
@EnableDiscoveryClient
@CrossOrigin
public class GoodsApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}
}
