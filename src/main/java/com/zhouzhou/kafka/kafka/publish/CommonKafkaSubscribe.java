package com.zhouzhou.kafka.kafka.publish;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.kafka.kafka.dto.TopicConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Description:可以模仿着写
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 11:22 AM
 */
@Component("commonKafkaSubscribe")
public class CommonKafkaSubscribe extends AbstractKafkaSubscribeWrapper{

    @KafkaListener(topics = TopicConstants.REG_PAY,groupId = "pay")
    public void listenPay(ConsumerRecord<String, String> record) throws Exception {
        logger.info(String.format("kafka 挂号收费消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
    }


    @KafkaListener(topics = TopicConstants.COMMON_PAY,groupId = "pay")
    public void listenXXXPay(ConsumerRecord<String, String> record , Acknowledgment ack) throws Exception {
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
        if (new Random().nextInt(100)<50){
            ack.acknowledge();
            logger.info(String.format("kafka 综合收费消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        }

    }
}
