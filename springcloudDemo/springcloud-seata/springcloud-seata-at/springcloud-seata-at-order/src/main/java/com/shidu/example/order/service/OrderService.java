package com.shidu.example.order.service;

import com.shidu.example.order.model.Order;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
public interface OrderService {
    Boolean create(Order order);
}
