package com.nqlz.hellorabbit;

import com.nqlz.hellorabbit.test.RabbitProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloRabbitApplication.class)
public class HelloRabbitApplicationTests {

    @Autowired
    RabbitProvider rabbitProvider;


    @Test
    public void contextLoads() {

        for(int a=0;a<10;a++){
            rabbitProvider.send();
        }
    }

}
