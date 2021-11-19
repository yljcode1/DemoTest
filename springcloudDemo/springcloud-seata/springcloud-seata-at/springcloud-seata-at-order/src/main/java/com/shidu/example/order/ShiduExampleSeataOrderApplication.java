package com.shidu.example.order;

import com.shidu.example.order.model.Order;
import com.shidu.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

import java.math.BigDecimal;

@SpringBootApplication
@RestController
@MapperScan("com.shidu.example.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class ShiduExampleSeataOrderApplication {

    private final OrderService orderService;
    @GetMapping("order/create")
    public Boolean create(Long userId,Long productId){
        Order order = new Order();
        order.setCount(1)
                .setMoney(BigDecimal.valueOf(88))
                .setProductId(productId)
                .setUserId(userId)
                .setStatus(0);
        return orderService.create(order);
    }
    public static void main(String[] args) {
        SpringApplication.run(ShiduExampleSeataOrderApplication.class, args);
    }

}
