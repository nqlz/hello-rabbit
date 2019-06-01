package com.nqlz.hellorabbit.reAck;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String exchange = "test_reAck_exchange";
        String routingKey = "test.reAck.save";

        for (int i=0;i<6;i++){
            String msg = "你好重回队列模式发送数据"+i;
            Map<String, Object> headers= new HashMap<>();
            headers.put("number",i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2) //持久化
                    .contentEncoding("UTF-8")
                    .headers(headers)
                    .build();
            channel.basicPublish(exchange, routingKey, true,properties, msg.getBytes());
        }
    }
}
