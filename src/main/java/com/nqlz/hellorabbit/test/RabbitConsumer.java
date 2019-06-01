package com.nqlz.hellorabbit.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloRabbit")
public class RabbitConsumer {

    @RabbitHandler
    public void chuli(String content){
        System.out.println("Consumer:哈哈"+content);
    }
}
