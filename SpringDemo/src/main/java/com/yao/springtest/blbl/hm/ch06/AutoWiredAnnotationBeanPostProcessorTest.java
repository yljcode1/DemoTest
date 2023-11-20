package com.yao.springtest.blbl.hm.ch06;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试AutowiredAnnotationBeanPostProcessor
 *
 * @date: 2023-11-17
 * @author: yao
 */
public class AutoWiredAnnotationBeanPostProcessorTest {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver()); // @Value

        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setBeanFactory(beanFactory);

        Bean1 bean1 = BeanUtils.instantiateClass(Bean1.class);
//        Bean1 bean1 = new Bean1();
        System.out.println(bean1);
        autowiredAnnotationBeanPostProcessor.postProcessProperties(null, bean1, "bean1");// 执行依赖注入 @Autowired @Resource
//        System.out.println(bean1);
        System.out.println(autowiredAnnotationBeanPostProcessor.getClass().equals(AutowiredAnnotationBeanPostProcessor.class));
        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        // 找到所有有@Autowired的东西注入
        InjectionMetadata injectionMetadata = (InjectionMetadata) findAutowiringMetadata.invoke(autowiredAnnotationBeanPostProcessor, "bean1", Bean1.class, null);

        // 调用InjectionMetadata 来进行依赖注入，注入时按照类型查找值
        injectionMetadata.inject(bean1, "bean1", null);

        //  按类型查找值
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(bean3, false);
        Object o = beanFactory.doResolveDependency(dependencyDescriptor, null, null, null);
        System.out.println(o);

        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), true);
        Object o1 = beanFactory.doResolveDependency(dd2, null, null, null);
        System.out.println(o1);

        Method setJavaHome = Bean1.class.getDeclaredMethod("setJavaHome", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setJavaHome, 0), true);
        Object o2 = beanFactory.doResolveDependency(dd3, null, null, null);
        System.out.println(o2);

    }

}
