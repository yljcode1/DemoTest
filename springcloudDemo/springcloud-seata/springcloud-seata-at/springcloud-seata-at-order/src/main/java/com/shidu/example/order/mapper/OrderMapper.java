package com.shidu.example.order.mapper;

import com.shidu.example.order.model.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
@Repository
public interface OrderMapper extends Mapper<Order> {
}
