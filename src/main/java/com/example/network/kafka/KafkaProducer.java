package com.example.network.kafka;

import com.example.network.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;

@Slf4j
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 自定义topic
     */
    public static final String TOPIC_TEST = "test";

    /**
     * 组别1
     */
    public static final String TOPIC_GROUP = "testGroup";

    public void pushMessage(String message){
        log.info("准备发送消息信息：{}", message);
        //发送消息
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(TOPIC_TEST, message);
        String dateTime = DateUtils.dateToString(new Date());
        log.info("topic：{}发送消息完成,完成时间：{}",TOPIC_TEST, dateTime);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error(TOPIC_TEST + "-生产者发送消息失败" + throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, Object> stringObjectSendResult) {
                String dateTime = DateUtils.dateToString(new Date());
                log.info(TOPIC_TEST + "-生产者发送消息成功" + stringObjectSendResult.toString() + "时间：" + dateTime);
            }
        });
    }
}
