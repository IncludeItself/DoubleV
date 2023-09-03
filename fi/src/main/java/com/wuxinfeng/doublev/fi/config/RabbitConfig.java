package com.wuxinfeng.doublev.fi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-14  20:03
 */
@Configuration
public class RabbitConfig {
//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

    @Bean
    public Exchange fiEventExchange(){
        return new TopicExchange("fi-event-exchange",true,false);
    }

    @Bean
    public Queue fiVoucherReverseQueue(){
        return new Queue("fi.voucher.reverse.queue",true,false,false);
    }

    @Bean
    public Binding fiVoucherReverseBinding(){
        return new Binding("fi.voucher.reverse.queue",Binding.DestinationType.QUEUE,"fi-event-exchange","fi.voucher.reverse.#",null);
    }
}
