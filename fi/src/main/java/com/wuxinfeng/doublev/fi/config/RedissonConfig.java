package com.wuxinfeng.doublev.fi.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-03-29  10:21
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;
    private String port;

    @Bean
    RedissonClient redissonClient() throws IOException{
        Config config=new Config();
        System.out.println("rediss://"+host+":"+port);
        config.useSingleServer().setAddress("redis://"+host+":"+port);
        return Redisson.create(config);
    }
}
