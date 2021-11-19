package com.shidu.example.storage;

import com.shidu.example.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@MapperScan("com.shidu.example.storage.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class ShiduExampleSeataStorageApplication {

    private final StorageService storageService;
    @GetMapping("storage/change")
    public Boolean changeStorage(Long productId,int used){
        return storageService.updateUseNum(productId,used);
    }
    public static void main(String[] args) {
        SpringApplication.run(ShiduExampleSeataStorageApplication.class, args);
    }

}
