package com.nqlz.hellorabbit.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;

@Data
public class mqUtil {
    //创建连接并获取channel
    static ConnectionFactory connectionFactory = new ConnectionFactory();
    public static Connection connection;

    static {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setPassword("123456");
            connectionFactory.setUsername("rabbit");
            connectionFactory.setVirtualHost("/");
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
