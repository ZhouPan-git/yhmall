package com.yhmall.configs;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nick
 * @Classname FeignConfig
 * @Date 2023/09/14 10:16
 * @Description
 */
@Configuration
public class FeignConfig {
	@Bean
	Logger.Level feignLoggerLevel() {
		//NONE,BASIC,HEADERS,FULL
		return Logger.Level.FULL;
	}
}
