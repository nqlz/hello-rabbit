package com.nqlz.hellorabbit.limit;


import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String exchange = "test_qos_exchange";
        String routingKey = "test.qos.save";
        String msg = "你好限流limit模式发送数据QOS";
        for (int i=0;i<6;i++){
            channel.basicPublish(exchange, routingKey, true,null, msg.getBytes());
        }
    }
}
