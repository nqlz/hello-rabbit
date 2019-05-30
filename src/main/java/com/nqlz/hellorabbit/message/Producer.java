package com.nqlz.hellorabbit.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.149");
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        Map<String, Object> herders=new HashMap<>();
        herders.put("username","战士");
        herders.put("message","德玛西亚之力");
        AMQP.BasicProperties properties= new AMQP.BasicProperties().builder()
                //设置为持久化，重启之后仍存在
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .headers(herders)

                .build();

        String msg = "Hello!!RabbitMQ!hhhh";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "queue001", properties, msg.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();
    }
}
