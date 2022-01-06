package com.yao.rocketmqdemo.base.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 批量消息生产者
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        List<Message> messages = new ArrayList<>();
        producer.start();
        Message message = new Message("BatchTopic", "Tag1", ("Hello World").getBytes(StandardCharsets.UTF_8));
        Message message2 = new Message("BatchTopic", "Tag1", ("Hello World").getBytes(StandardCharsets.UTF_8));
        Message message3 = new Message("BatchTopic", "Tag1", ("Hello World").getBytes(StandardCharsets.UTF_8));

        messages.add(message);
        messages.add(message2);
        messages.add(message3);
        // 设置延迟时间
        message.setDelayTimeLevel(2);
        SendResult send = producer.send(messages);

        SendStatus sendStatus = send.getSendStatus();
        System.out.println("发送结果" + sendStatus);

        TimeUnit.SECONDS.sleep(1);
        producer.shutdown();
    }
}
