package com.nqlz.hellorabbit.deadLetX;

import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        //普通队列
        String exchange = "test_dlx_exchange";
        String routingKey = "test.dlx.#";
        String queueName = "test_dlx_queue";
        //死信队列
        String dlx_exchange="dlx.exchange";
        String dlx_queue="dlx.queue";
        String dlx_routingKey="#";

        //声明死信队列
        channel.exchangeDeclare(dlx_exchange,"topic",true,false,null);
        channel.queueDeclare(dlx_queue,true,false,false,null);
        channel.queueBind(dlx_queue,dlx_exchange,dlx_routingKey);
        //声明目标普通队列
        channel.exchangeDeclare(exchange, "topic", true,false,null);
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange", dlx_exchange);
        arguments.put("x-dead-letter-routing-key", dlx_routingKey);
        channel.queueDeclare(queueName,true,false,false,arguments);
        channel.queueBind(queueName,exchange,routingKey);

        //监听队列，自动签收
        channel.basicConsume(queueName,true,new myConsumer(channel));


    }
}
