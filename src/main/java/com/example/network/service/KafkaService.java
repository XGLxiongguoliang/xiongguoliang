package com.example.network.service;

import org.springframework.stereotype.Service;

/**
 * @program com.example.demo.service
 * @description kafka service
 * @auther Mr.Xiong
 * @create 2022-05-09 13:10
 */
@Service
public interface KafkaService {
    public void sendMessage(String topic, String msg);
}
