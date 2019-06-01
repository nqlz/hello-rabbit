package com.nqlz.hellorabbit.reAck;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class myConsumer extends DefaultConsumer {
    private Channel channel;

    public myConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("自定义消费：");
        System.err.println("body:---" + new String(body));
        List<Integer> integers = Arrays.asList(0, 5, 4);
        Integer number = (Integer) properties.getHeaders().get("number");
        if (integers.contains(number)) {
            System.out.println(number+"不被消费");
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
                //处理消息,发送应答，multiple是否批量签收
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
