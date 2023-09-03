package com.wuxinfeng.doublev.mm;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: ${YEAR}-${MONTH}-${DAY}  ${HOUR}:${MINUTE}
 */
@EnableRabbit
@EnableFeignClients
@SpringBootApplication
public class MMApplication {
    public static void main(String[] args) {
        SpringApplication.run(MMApplication.class,args);
    }
}