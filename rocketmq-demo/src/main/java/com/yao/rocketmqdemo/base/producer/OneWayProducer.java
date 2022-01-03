package com.yao.rocketmqdemo.base.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * 单向发送消息
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my_producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();


        for (int i = 0; i < 10; i++) {
            Message message = new Message("Topic", "TagA", ("Hello RockerMQ" + i).getBytes(StandardCharsets.UTF_8));
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(message);
        }
        // 如果消息不在发送，将会关闭Producer实例
        producer.shutdown();
    }
}
