package com.zhouzhou.kafka.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "支付信息对象")
@AllArgsConstructor
@NoArgsConstructor
public class JHBankBo {
    @ApiModelProperty(value = "支付通道code")
    private String payChannelCode;
    @ApiModelProperty(value = "支付通道名字")
    private String payChannelName;
    @ApiModelProperty(value = "分润比例")
    private String distributionRation;
    @ApiModelProperty(value = "商户号")
    private String mrChId;
    @ApiModelProperty(value = "分账规则编码")
    private String clrgRuleId;

}
