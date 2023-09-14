package com.yhmall.api;

import com.yhmall.configs.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Nick
 * @Classname OrderApi
 * @Date 2023/09/14 10:18
 * @Description
 */
@FeignClient(value = "order",path = "",configuration = FeignConfig.class)
public interface OrderApi {
}
