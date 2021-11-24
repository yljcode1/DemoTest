package com.yao.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaoK
 * @date 2021/11/24
 */
@FeignClient("storage-service")
public interface StorageFegin {
    @GetMapping("storage/change")
    public Boolean changeStorage(Long productId,int used);
}
