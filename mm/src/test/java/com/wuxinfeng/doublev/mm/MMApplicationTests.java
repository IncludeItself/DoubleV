package com.wuxinfeng.doublev.mm;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-10  23:22
 */
@RequiredArgsConstructor
public class MMApplicationTests {
    private final AmqpAdmin amqpAdmin;
    private final RabbitTemplate rabbitTemplate;


    @Test
    public void createExchange(){
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    public void createQueue(){
        Queue queue = new Queue("hello-java-queue",true,false,true);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createBinding(){
        Binding binding = new Binding("hello-java-queue",
                Binding.DestinationType.QUEUE,
                "hello-java-exchange",
                "hello-java",
                null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void sendMessageTest(){
        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java","Hello World");//最后一个参数可以是对象，要实现序列化接口

    }


    //@RabbitListener 标在类或方法上，监听需要的队列，多个
    // @RabbitHandler 标在方法上，重载不同的方法

}
