package com.yao.rocketmqdemo.base.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 延迟消费生产者
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("base", "Tag1", ("Hello World" + i).getBytes(StandardCharsets.UTF_8));
            // 设置延迟时间
            message.setDelayTimeLevel(2);
            SendResult send = producer.send(message);

            SendStatus sendStatus = send.getSendStatus();
            System.out.println("发送结果" + sendStatus);

            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();
    }
}
