package com.nqlz.hellorabbit.consumer;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String exchange = "test_consumer_exchange";
        String routingKey = "test.consumer.save";
        String msg = "你好自定义consumer模式发送数据";
        for (int i=0;i<6;i++){
            channel.basicPublish(exchange, routingKey, true,null, msg.getBytes());
        }
    }
}
