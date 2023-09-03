package com.wuxinfeng.doublev.fi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class FiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiApplication.class, args);
    }

}
