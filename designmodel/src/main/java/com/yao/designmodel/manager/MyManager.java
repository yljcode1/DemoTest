package com.yao.designmodel.manager;

/**
 * @author xiaoK
 * @date 2021/11/11
 */
public interface MyManager {
    String begin (String id,String name,int timeOut);
    String commit(String id);
}
