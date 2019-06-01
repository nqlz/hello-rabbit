package com.nqlz.hellorabbit.message;

import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws Exception{
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare("queue001",true,false,false,null);
        //创建消费者
        QueueingConsumer queueingConsumer=new QueueingConsumer(channel);
        //设置channel
        channel.basicConsume("queue001",true,queueingConsumer);

        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端："+msg);
            Map<String, Object> headers = delivery.getProperties().getHeaders();
            System.out.println(headers.get("username"));
            System.out.println(headers.get("message"));
        }

    }
}
