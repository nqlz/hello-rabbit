package com.nqlz.hellorabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String content="你好MQ,hh"+new Date();
        System.out.println("Provider:提供者"+content);
        amqpTemplate.convertAndSend("helloRabbit",content);
    }
}
