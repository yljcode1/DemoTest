package com.yao.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @date: 2022-10-19
 * @author: yao
 */
@Configuration
@RequiredArgsConstructor
public class DirectRabbitConfig implements BeanPostProcessor {
    private final RabbitAdmin rabbitAdmin;

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        return new Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        //Direct交换机
        return new DirectExchange(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, true, false);
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //创建交换机
        rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        //创建队列
        rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
        return null;
    }
    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqDemoDirectQueue())
                //到交换机
                .to(rabbitmqDemoDirectExchange())
                //并设置匹配键
                .with(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING);
    }
}
