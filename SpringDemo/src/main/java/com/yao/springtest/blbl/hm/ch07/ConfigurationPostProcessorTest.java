package com.yao.springtest.blbl.hm.ch07;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

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
//        context.registerBean(ConfigurationClassPostProcessor.class);//@ComponentScan @Bean @Import @ImportResource
//        context.registerBean(MapperScannerConfigurer.class, bd -> {
//            bd.getPropertyValues().add("basePackage", "com.yao.springtest.blbl.hm.ch07.mapper");
//        });
//        context.refresh();
//        context.registerBean(ConfigurationClassPostProcessor.class);
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader metaReader = factory.getMetadataReader(new ClassPathResource("com/yao/springtest/blbl/hm/ch07/Config.java"));
        Set<MethodMetadata> methods = metaReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata method : methods) {
            System.out.println(method);
            String initMethod = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
            beanDefinitionBuilder.setFactoryMethodOnBean(method.getMethodName(), "config");
            beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            if (initMethod.length() > 0) {
                beanDefinitionBuilder.setInitMethodName(initMethod);
            }
            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            context.getDefaultListableBeanFactory().registerBeanDefinition(method.getMethodName(), beanDefinition);
        }

        ComponentScan componentScanAnno = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (ObjectUtil.isNotEmpty(componentScanAnno)) {
            for (String basePackage : componentScanAnno.basePackages()) {
                String path = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                // beanName生成器
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                System.out.println(basePackage);
                // 可以获取到所有的Resource
                Resource[] resources = context.getResources(path);
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                for (Resource resource : resources) {
                    System.out.println(resource);
                    MetadataReader reader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    System.out.println("类名:" + reader.getClassMetadata().getClassName());
                    // 是否添加了@Component注解
                    System.out.println(reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                    // 是否添加了派生
                    System.out.println(reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));
                    if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()) || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                        AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();
                        String beanName = generator.generateBeanName(bd, context.getDefaultListableBeanFactory());
                        context.getDefaultListableBeanFactory().registerBeanDefinition(beanName, bd);
                    }
                }


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
