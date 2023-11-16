package com.yao.springtest;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        // ApplicationContext 和 BeanFactory 相对比
        // 查看继承关系，ApplicationContext继承并扩展BeanFactory，拥有BeanFactory的功能

        ConfigurableApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.entrySet().stream().filter(e -> e.getKey().startsWith("component")).forEach(
                System.out::println
        );
//        map.forEach((k,v)-> System.out.println(k+"=="+v));

        // ApplicationContext 相比于BeanFactory能多做些什么
        // App相比于BeanFactory 多扩展了MessageSource,EnvironmentCapable,AutoCloseable
//        System.out.println(context.getMessage("hi", null, Locale.CHINESE));
//        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));
//        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));

        Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        System.out.println(context.getEnvironment().getProperty("java_home"));
        System.out.println(context.getEnvironment().getProperty("server.port"));
        //
//        context.publishEvent(new UserRegisterdEvent(context));
        //时间监听
        context.getBean("component1", Component1.class).register();



        context.getBean(RegisterService.class).registerAndSms();
    }

}
