package com.yao.rocketmqdemo.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 发送同步消息
 */
public class SynProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 1、创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("my_producer");
        // 2、指定Nameserver地址
        producer.setNamesrvAddr("localhost:9876");
        // 3、启动producer
        producer.start();
        // 4、创建消息对象，指定主题Topic、Tag和消息体
        for (int i = 0;i<10;i++){
            // 5、发送消息
            Message message = new Message("base","tag",("Hello world"+i).getBytes(StandardCharsets.UTF_8));
            SendResult send = producer.send(message);
            SendStatus sendStatus = send.getSendStatus();
            System.out.println("发送的结果是"+sendStatus);
            TimeUnit.SECONDS.sleep(1);
        }
        // 6、关闭生产者producer
        producer.shutdown();
    }
}
