package com.yao.springtest.blbl.hm.ch07;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @date: 2023-11-20
 * @author: yao
 */
public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        ComponentScan componentScanAnno = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (ObjectUtil.isNotEmpty(componentScanAnno)) {
            for (String basePackage : componentScanAnno.basePackages()) {
                String path = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                // beanName生成器
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                System.out.println(basePackage);
                // 可以获取到所有的Resource
                Resource[] resources;
                try {
                    resources = new PathMatchingResourcePatternResolver().getResources(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                for (Resource resource : resources) {
                    System.out.println(resource);
                    MetadataReader reader = null;
                    try {
                        reader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("类名:" + reader.getClassMetadata().getClassName());
                    // 是否添加了@Component注解
                    System.out.println(reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                    // 是否添加了派生
                    System.out.println(reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));
                    if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()) || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                        AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();
                        if (beanFactory instanceof DefaultListableBeanFactory) {
                            DefaultListableBeanFactory beanFactory1 = (DefaultListableBeanFactory) beanFactory;
                            String beanName = generator.generateBeanName(bd, beanFactory1);
                            beanFactory1.registerBeanDefinition(beanName, bd);
                        }

                    }
                }

            }
        }
    }
}
