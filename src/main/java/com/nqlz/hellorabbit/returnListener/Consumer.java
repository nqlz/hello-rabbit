package com.nqlz.hellorabbit.returnListener;

import com.nqlz.hellorabbit.config.mqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Connection connection = mqUtil.connection;
        Channel channel = connection.createChannel();

        String routingKey = "test.return.#";
        String exchange = "test_return_exchange";
        String queueName = "test_return_queue";
        //exchange名称,类型，是否持久化
        channel.exchangeDeclare(exchange, "topic", true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        //绑定交换机与队列
        channel.queueBind(queueName,exchange,routingKey);
        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列，自动签收
       channel.basicConsume(queueName,true,queueingConsumer);
       while (true){
           QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
           String msg = new String(delivery.getBody());
           System.out.println("消费端："+msg);
       }


    }
}
