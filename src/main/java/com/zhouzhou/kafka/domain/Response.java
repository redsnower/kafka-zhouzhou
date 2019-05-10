package com.zhouzhou.kafka.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * Description:通用返回类
 * User: zhouzhou
 * Date: 2019-03-28
 * Time: 3:49 PM
 */
@Data
public class Response<T> implements Serializable {

    private String code = "200"; // 200: 成功 其他失败
    private String msg;
    private T data;
    private Object links;

    public interface  ResponseResult{
        String SUCCESS = "200";
        String FAIL = "-1";
    }

}
