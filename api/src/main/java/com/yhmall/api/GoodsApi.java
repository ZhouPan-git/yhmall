package com.yhmall.api;

import com.yhmall.configs.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Nick
 * @Classname GoodsApi
 * @Date 2023/09/14 10:19
 * @Description
 */
@FeignClient(value = "goods",path = "",configuration = FeignConfig.class)
public interface GoodsApi {
}
