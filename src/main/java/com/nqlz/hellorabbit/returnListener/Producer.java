package com.nqlz.hellorabbit.returnListener;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();
        //指定消息投递模式:消息确认模式

        String exchange = "test_return_exchange";
        String routingKey = "test.return.save";
        String routingKeyErr = "err.save";
        String msg = "你好Return模式发送数据";

        //发送消息
//        channel.basicPublish(exchange, routingKey, true,null, msg.getBytes());
        channel.basicPublish(exchange, routingKeyErr, true,null, msg.getBytes());
        //添加return监听
        channel.addReturnListener(new ReturnListener() {
            //接受路由不可达的消息
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println(new String(body));
            }
        });


    }
}
