package com.yao.springtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试模板方法
 * 模版方法，主要作用可以扩展，在于回调，要实现的功能，采用接口的方法抽离出来，
 * 其他功能静态，比如说这里面的依赖注入
 *
 * @date: 2023-11-15
 * @author: yao
 */
public class TestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        myBeanFactory.addProcessors(object -> System.out.println("如果我添加了扩展@Autoware方法的解释"));
        myBeanFactory.addProcessors(object -> System.out.println("如果我添加了扩展@Configure方法的解释"));
    }

    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造" + bean);
            System.out.println("依赖注入" + bean);
            for (BeanPostProcessor x : processors) {
                x.inject(bean);
            }
            System.out.println("初始化" + bean);
            return bean;
        }

        private List<BeanPostProcessor> processors = new ArrayList<>();

        public void addProcessors(BeanPostProcessor beanPostProcessor) {
            processors.add(beanPostProcessor);
        }
    }

    interface BeanPostProcessor {
        void inject(Object object); // 依赖注入阶段扩展
    }

    abstract class DefaultBeanPostProcessor implements BeanPostProcessor {

    }
}
