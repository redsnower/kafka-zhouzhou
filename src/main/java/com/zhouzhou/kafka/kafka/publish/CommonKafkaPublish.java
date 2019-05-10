package com.zhouzhou.kafka.kafka.publish;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.kafka.kafka.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Description:通用 kafka 发布
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 5:25 PM
 */
@Component("commonKafkaPublish")
public class CommonKafkaPublish extends AbstractKafkaPublishWrapper {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler producerListener;

    @Override
    public <T> Boolean publish(KafkaMessage<T> kafkaMessage) {

        kafkaTemplate.setProducerListener(producerListener);

        String applicationName = kafkaMessage.getApplicationName();
        String topic = kafkaMessage.getTopic();
        String messageId = kafkaMessage.getMessageId();
        Object content = kafkaMessage.getContent();

        String kafkaValue = JSONObject.toJSONString(content);


        try {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, kafkaValue);
            future.get();
        } catch (Exception e) {
            logger.error(String.format("kafka 调用出现异常,messageId{%s},发起服务为{%s},topic 为{%s},消息体为{%s}", messageId, applicationName, topic, kafkaValue));
           return false;
        }

        return true;
    }
}
