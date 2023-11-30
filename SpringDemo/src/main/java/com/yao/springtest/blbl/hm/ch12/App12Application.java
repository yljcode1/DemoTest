package com.yao.springtest.blbl.hm.ch12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例注入多例，多例失效，因为只注入了一次
 * @date: 2023-11-28
 * @author: yao
 */
@Slf4j
@SpringBootApplication
public class App12Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App12Application.class);
        E e = context.getBean(E.class);
        log.info("{}", e.getF1());
        log.info("{}", e.getF1());
        log.info("{}", e.getF1());
//        SpringApplication.run(App12Application.class, args);
    }
}
