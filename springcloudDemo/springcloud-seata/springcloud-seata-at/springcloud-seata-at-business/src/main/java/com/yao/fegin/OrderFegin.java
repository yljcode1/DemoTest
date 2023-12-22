package com.yao.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaoK
 * @date 2021/11/24
 */
@FeignClient(value = "order-service")
public interface OrderFegin {
    @GetMapping("order/create")
    Boolean create(Long userId, Long productId);
}
