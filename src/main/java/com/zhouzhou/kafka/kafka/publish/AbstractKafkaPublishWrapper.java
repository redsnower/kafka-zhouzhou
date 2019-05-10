package com.zhouzhou.kafka.kafka.publish;

import com.zhouzhou.kafka.kafka.dto.KafkaMessage;
import com.zhouzhou.kafka.kafka.dto.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Description: kafka 消息生产者适配器
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 5:19 PM
 */
public abstract class AbstractKafkaPublishWrapper implements IKafkaPublish {

    protected Logger logger = LoggerFactory.getLogger(LoggerName.KAFKA);


    @Override
    public <T> Boolean send(KafkaMessage<T> kafkaMessage) {
        if (kafkaMessage == null
                || StringUtils.isEmpty(kafkaMessage.getApplicationName())
                || StringUtils.isEmpty(kafkaMessage.getMessageId())
                || StringUtils.isEmpty(kafkaMessage.getTopic())
                || kafkaMessage.getContent() == null){
            throw new RuntimeException(String.format("入参缺失,{%s}", kafkaMessage));

        }
        return publish(kafkaMessage);
    }

    public abstract <T> Boolean publish(KafkaMessage<T> kafkaMessage);

}
