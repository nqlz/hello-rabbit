package com.nqlz.hellorabbit.limit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class myConsumer extends DefaultConsumer {
    private Channel channel;
    public myConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("自定义消费：");
        System.err.println("consumerTag:---"+consumerTag);
        System.err.println("envelope:---"+envelope);
        System.err.println("properties:---"+properties);
        System.err.println("body:---"+new String(body));

        //处理消息,发送应答，multiple是否批量签收
        channel.basicAck(envelope.getDeliveryTag(),false);
    }
}
