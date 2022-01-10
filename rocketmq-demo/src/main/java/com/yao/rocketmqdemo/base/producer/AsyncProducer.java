package com.yao.rocketmqdemo.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * 发送异步消息
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建producer组
        DefaultMQProducer producer = new DefaultMQProducer("my_producer");
        // 创建NameServer
        producer.setNamesrvAddr("localhost:9876");
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 开启producer
        producer.start();

        int messageCount = 100;
        // 根据消息数量实例化倒计时计算器
        final CountDownLatch2 countDownLatch2 = new CountDownLatch2(messageCount);
        for (int i = 1; i <= messageCount; i++) {
            // 创建消息
            final int index = i;
            Message message = new Message("my_topic", "my_tags", "OrderID188", ("Hello world" + i).getBytes(StandardCharsets.UTF_8));
            // 发送消息
            // SendCallback接收异步返回结果的回调
            int finalI = i;
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch2.countDown();
                    System.out.printf("%10d OK %s %n", finalI, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.printf("%-10d Exception %s %n", finalI, throwable);
                    throwable.printStackTrace();
                }
            });
        }
        // 关闭producer
        producer.shutdown();
    }
}
