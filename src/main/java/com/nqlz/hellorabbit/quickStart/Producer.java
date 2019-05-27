package com.nqlz.hellorabbit.quickStart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.150");
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        String msg = "Hello!!RabbitMQ!hhhh";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "queue001", null, msg.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();
    }
}
