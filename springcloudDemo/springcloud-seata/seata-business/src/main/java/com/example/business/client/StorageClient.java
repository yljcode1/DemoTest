package com.example.business.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
@FeignClient(name = "storage-service")
@Component
public interface StorageClient {
    @GetMapping("storage/change")
    Boolean changeStorage(@RequestParam("productId") Long productId , @RequestParam("used")  int used);
}
