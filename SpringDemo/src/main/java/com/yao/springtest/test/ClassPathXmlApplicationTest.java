package com.yao.springtest.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @date: 2023-11-09
 * @author: yao
 */

public class ClassPathXmlApplicationTest {
    public static void main(String[] args) {
        // 默认的refresh方法走的就是DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // xml定义读取，
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 读取文件
        reader.loadBeanDefinitions(new ClassPathResource("xxx.xml"));
        beanFactory.getBeanDefinitionNames();

    }
}
