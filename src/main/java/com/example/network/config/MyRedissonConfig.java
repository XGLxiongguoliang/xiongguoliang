package com.example.network.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program com.example.network.config
 * @description redisson分布式锁配置
 * @auther Mr.Xiong
 * @create 2022-10-23 15:16:09
 */
@Configuration
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    RedissonClient createRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setPassword("12345")
                .setDatabase(0)
                .setAddress("redis://8.140.139.185:6379");
        return Redisson.create(config);
    }
}
