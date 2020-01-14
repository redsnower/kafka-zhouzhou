package com.zhouzhou.kafka.controller;

import com.zhouzhou.kafka.domain.JHBankBo;
import com.zhouzhou.kafka.domain.Response;
import com.zhouzhou.kafka.kafka.dto.KafkaMessage;
import com.zhouzhou.kafka.kafka.dto.TopicConstants;
import com.zhouzhou.kafka.kafka.publish.IKafkaPublish;
import com.zhouzhou.kafka.kafka.util.KafkaUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description:
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 10:47 PM
 */
@Api("测试")
@RestController
@Slf4j
public class TestController {

    @Autowired
    private IKafkaPublish kafkaPublish;

    
    
    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping("/log4j")
    public Response<Boolean> log4j() {
    	 log.info("输出信息……");
         log.trace("随意打印……");
         log.debug("调试信息……");
         log.warn("警告信息……");
         try {
             throw new Exception("错误消息啊");
         } catch (Exception e) {
         	log.error("处理业务逻辑的时候发生一个错误……", e);
         }
		return null;
    }
    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping("/demo/publishMessage")
    public Response<Boolean> publishMessage(Integer counts) {

        Response<Boolean> response = new Response<>();
        Integer success = 0;

        for (int i = 0; i < counts; i++) {
            JHBankBo jhBankBo = new JHBankBo();
            jhBankBo.setClrgRuleId("12345");
            jhBankBo.setPayChannelName("支付宝");
            KafkaMessage<Object> kafkaMessage = KafkaMessage.builder().applicationName("order")
                    .messageId(KafkaUtils.generateMessageId())
                    .topic(TopicConstants.REG_PAY)
                    .content(jhBankBo).build();
            Boolean flag = kafkaPublish.send(kafkaMessage);

            if (flag) {
                success++;
            }
        }

        response.setData(true);
        response.setMsg(String.format("发送消息,成功{%s},失败{%s}", success, counts - success));
        return response;

    }

    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping("/demo/publishMessage2")
    public Response<Boolean> publishMessage2(Integer counts) {

        Response<Boolean> response = new Response<>();
        Integer success = 0;

        for (int i = 0; i < counts; i++) {
            JHBankBo jhBankBo = new JHBankBo();
            jhBankBo.setClrgRuleId("12345");
            jhBankBo.setPayChannelName("微信");
            KafkaMessage<Object> kafkaMessage = KafkaMessage.builder().applicationName("order")
                    .messageId(KafkaUtils.generateMessageId())
                    .topic(TopicConstants.COMMON_PAY)
                    .content(jhBankBo)
                    .autoAck(false)
                    .build();
            Boolean flag = kafkaPublish.send(kafkaMessage);

            if (flag) {
                success++;
            }
        }

        response.setData(true);
        response.setMsg(String.format("发送消息,成功{%s},失败{%s}", success, counts - success));
        return response;

    }
}
