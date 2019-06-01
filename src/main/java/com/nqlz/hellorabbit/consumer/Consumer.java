package com.nqlz.hellorabbit.consumer;

import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String routingKey = "test.consumer.#";
        String exchange = "test_consumer_exchange";
        String queueName = "test_consumer_queue";

        channel.exchangeDeclare(exchange, "topic", true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchange,routingKey);

        //监听队列，自动签收
       channel.basicConsume(queueName,true,new myConsumer(channel));


    }
}
