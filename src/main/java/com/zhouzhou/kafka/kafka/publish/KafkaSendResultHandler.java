package com.zhouzhou.kafka.kafka.publish;

import com.zhouzhou.kafka.kafka.dto.LoggerName;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSendResultHandler implements ProducerListener {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.KAFKA);

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("手动 ack 方式Kafka Message send success : " + producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.error("手动 ack 方式 Kafka Message send error : " + producerRecord.toString(), exception);
    }
}