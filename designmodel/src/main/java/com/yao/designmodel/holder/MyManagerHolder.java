package com.yao.designmodel.holder;

import com.yao.designmodel.manager.MyManager;

/**
 * @author xiaoK
 * @date 2021/11/11
 */
public class MyManagerHolder {

    private static class SingletonHolder{
        private static MyManager INSTANCE = null;

    }
}
