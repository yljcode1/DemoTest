package com.yao.springtest.blbl.hm.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@SpringBootApplication
public class IdeaTest {
    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(IdeaTest.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Hello.class);
        for (String beanDefinitionName : Arrays.stream(context.getBeanDefinitionNames()).filter(x -> x.contains("Hello")).collect(Collectors.toList())) {
            System.out.println(beanDefinitionName);
        }
        Hello bean = context.getBean(ChinaHello.class);
        bean.syout();
    }
}

interface Hello {
    void syout();
}

@Component
class ChinaHello implements Hello {

    @Override
    public void syout() {
        System.out.println("this is ChineseHello");

    }
}

@Component
class EnglishHello implements Hello {

    @Override
    public void syout() {
        System.out.println("this is EnglishHello");
    }
}