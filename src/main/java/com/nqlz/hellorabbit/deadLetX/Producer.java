package com.nqlz.hellorabbit.deadLetX;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String exchange = "test_dlx_exchange";
        String routingKey = "test.dlx.save";
        String msg = "你好死信队列模式发送数据";
        for (int i=0;i<6;i++){
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2) //持久化
                    .expiration("10000")
                    .contentEncoding("UTF-8")
                    .build();
            channel.basicPublish(exchange, routingKey, true,properties, msg.getBytes());
        }
    }
}
