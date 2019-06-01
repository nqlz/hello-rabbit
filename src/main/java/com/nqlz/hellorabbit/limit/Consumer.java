package com.nqlz.hellorabbit.limit;

import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String exchange = "test_qos_exchange";
        String routingKey = "test.qos.#";
        String queueName = "test_qos_queue";

        channel.exchangeDeclare(exchange, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchange, routingKey);

        //限流方式，自动签收autoACK设置为false
        //prefetchSize消息大小限制，prefetchCount一次消费数量，global应用为channel级别还是consumer级别,一般设置为false
        channel.basicQos(0, 1, false);
        channel.basicConsume(queueName, false, new myConsumer(channel));


    }
}
