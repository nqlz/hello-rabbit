package com.nqlz.hellorabbit.confirm;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        //指定消息投递模式:消息确认模式
        channel.confirmSelect();

        String exchange = "test_confirm_exchange";
        String routingKey = "test.confirm.save";
        String msg = "你好Confirm模式发送数据";
        //发送消息
        channel.basicPublish(exchange, routingKey, null, msg.getBytes());
        //添加确认监听
        channel.addConfirmListener(new ConfirmListener() {
           @Override
           public void handleAck(long l, boolean b) throws IOException {
               System.out.println("------------confirm----Ack");
           }
           //返回失败时
           @Override
           public void handleNack(long l, boolean b) throws IOException {
               System.out.println("------------confirm----NoAck");
           }
       });

    }
}
