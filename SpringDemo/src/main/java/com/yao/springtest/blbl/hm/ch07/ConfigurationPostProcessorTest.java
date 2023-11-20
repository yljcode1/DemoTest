package com.yao.springtest.blbl.hm.ch07;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @date: 2023-11-18
 * @author: yao
 */
@Slf4j
public class ConfigurationPostProcessorTest {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);//@ComponentScan @Bean @Import @ImportResource
        context.registerBean(MapperScannerConfigurer.class, bd -> {
            bd.getPropertyValues().add("basePackage", "com.yao.springtest.blbl.hm.ch07.mapper");
        });
//        context.refresh();
//        context.registerBean(ConfigurationClassPostProcessor.class);

        ComponentScan componentScanAnno = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (ObjectUtil.isNotEmpty(componentScanAnno)) {
            for (String basePackage : componentScanAnno.basePackages()) {
                String path = "classppath*:" + basePackage.replace(".", "/") + "/**/*.class";
                System.out.println(basePackage);
                Resource[] resources = context.getResources(path);

            }
        }
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));
//        IntStream.range(1, 8).mapToObj(i -> (Runnable)() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("current " +i + ", threadName" + Thread.currentThread());
//        }).forEach(threadPoolExecutor::execute);
    }
}
