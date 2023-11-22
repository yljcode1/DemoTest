package com.yao.springtest.blbl.hm.ch09.aop;

import com.yao.springtest.blbl.hm.ch09.aop.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@Slf4j
@SpringBootApplication
public class App09Application {
    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(App09Application.class);
//        MyService service = context.getBean(MyService.class);
//        log.info("service class:{}", service.getClass());
//        service.foo();
//        context.close();
        new MyService().foo();
    }
}
