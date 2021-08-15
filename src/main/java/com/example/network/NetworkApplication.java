package com.example.network;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan("com.example.network.mapper")
@SpringBootApplication
public class NetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkApplication.class, args);
    }

}
