package com.yao.config;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @date: 2022-10-19
 * @author: yao
 */
@Component
@RabbitListener(queuesToDeclare =@Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC))
public class RabbitDemoConsumer {
    @RabbitHandler
    public void process(Map map){
        System.out.println("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息"+map.toString());
    }
}
