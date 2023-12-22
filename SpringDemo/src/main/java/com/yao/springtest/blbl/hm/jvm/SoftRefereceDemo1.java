package com.yao.springtest.blbl.hm.jvm;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @date: 2023-12-18
 * @author: yao
 */
public class SoftRefereceDemo1 {
    public static void main(String[] args) {
        Cache<Object, Object> build = Caffeine.newBuilder().softValues().build();
    }
}
