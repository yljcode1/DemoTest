package com.yao.controller;

import com.yao.fegin.OrderFegin;
import com.yao.fegin.StorageFegin;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaoK
 * @date 2021/11/24
 */
@Controller
@RequiredArgsConstructor
public class BusinessController {
    public final OrderFegin orderFegin;
    public final StorageFegin storageFegin;
    @GetMapping("/buy")
    @GlobalTransactional
    public void buy(@Param("userId")Long userId,@Param("productId")Long productId){
        orderFegin.create(userId,productId);
        storageFegin.changeStorage(productId,1);
    }
}
