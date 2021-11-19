package com.shidu.example.order.service;

import com.shidu.example.order.mapper.OrderMapper;
import com.shidu.example.order.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;


    @Override
    @Transactional
    public Boolean create(Order order) {
        log.info("创建订单开始");
        int index = orderMapper.insert(order);
        log.info("创建订单结束");
        return index>0;
    }
}
