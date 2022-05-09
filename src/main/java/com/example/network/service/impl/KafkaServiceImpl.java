package com.example.network.service.impl;

import com.example.network.kafka.KafkaProducer;
import com.example.network.service.KafkaService;
import com.example.network.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program com.example.demo.service
 * @description kafka service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void sendMessage(String topic, String msg) {
        kafkaProducer.pushMessage("查询啦哈哈哈--- " + DateUtils.dateToString(new Date()));
    }
}
