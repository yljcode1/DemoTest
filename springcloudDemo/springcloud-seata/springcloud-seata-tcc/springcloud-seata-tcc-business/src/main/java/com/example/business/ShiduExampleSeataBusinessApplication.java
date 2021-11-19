package com.example.business;

import com.example.business.client.OrderClient;
import com.example.business.client.StorageClient;
import io.seata.core.model.TransactionManager;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.TransactionManagerHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@RequiredArgsConstructor
public class ShiduExampleSeataBusinessApplication {


    private final OrderClient orderClient;

    private final StorageClient storageClient;

    @GetMapping("buy")
    @GlobalTransactional
    public String buy(Long userId,Long productId){
        //远程调用order服务创建订单
        orderClient.create(userId,productId);

        //远程调用库存服务修改库存
        storageClient.changeStorage(userId,1);

    //    throw new RuntimeException("xxxx");
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(ShiduExampleSeataBusinessApplication.class, args);

        TransactionManager transactionManager = TransactionManagerHolder.get();
        System.out.println(transactionManager);
    }

}
