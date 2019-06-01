package com.nqlz.hellorabbit.deadLetX;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class myConsumer extends DefaultConsumer {
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public myConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("自定义消费：");
        System.err.println("consumerTag:---"+consumerTag);
        System.err.println("envelope:---"+envelope);
        System.err.println("properties:---"+properties);
        System.err.println("body:---"+new String(body));
    }
}
