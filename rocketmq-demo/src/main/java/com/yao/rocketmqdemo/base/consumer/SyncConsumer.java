package com.yao.rocketmqdemo.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者
 */
public class SyncConsumer {
    public static void main(String[] args) throws MQClientException {
        // 指定消费者组
        DefaultMQPushConsumer consumerGroup1 = new DefaultMQPushConsumer("consumerGroup1");
        // 指定NameServer
        consumerGroup1.setNamesrvAddr("localhost:9876");
        // 订阅Topic
        consumerGroup1.subscribe("Topic", "*");
        // 负载均衡模式消费
        // 广播模式和负载均衡模式
        // 广播模式: 多个消费者将接收到生产者所有的的消息，负载均衡：分别消费部分消息,默认负载均衡
        consumerGroup1.setMessageModel(MessageModel.CLUSTERING);
        //广播模式
//        consumerGroup1.setMessageModel(MessageModel.BROADCASTING);
        // 注册回调函数，处理消息
        consumerGroup1.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumerGroup1.start();
        System.out.println("consumer Started.%n");
    }
}
