package com.example.business.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service")
@Component
public interface OrderClient {

    @GetMapping("order/create")
    Boolean create(@RequestParam("userId") Long userId,@RequestParam("productId") Long productId);
}
