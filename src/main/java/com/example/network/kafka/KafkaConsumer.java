package com.example.network.kafka;

import com.example.network.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,groupId = KafkaProducer.TOPIC_GROUP)
    public void consumerGroup1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        String dateTime = DateUtils.dateToString(new Date());
        log.info("group接收到消息时间：{}", dateTime);
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            Object msg = message.get();
            log.info("consumerGroup 消费了： Topic:" + topic + ",Message:" + msg);
            //手动提交偏移量
            ack.acknowledge();
        }
    }
}
