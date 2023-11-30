package com.yao.springtest.blbl.hm.ch12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-28
 * @author: yao
 */
@Component
public class E {
    @Autowired
    private F1 f1;

    public F1 getF1() {
        return f1;
    }
}
