package com.taofang.webapi.result;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class Result {
    // 结果Code
    private int code;
    // 错误消息
    private List<String> failMessages;

    public Result() {
    }

    public Result(int code, List<String> failMessages) {
        this.code = code;
        this.failMessages = failMessages;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getFailMessages() {
        return failMessages;
    }

    public void setFailMessages(List<String> failMessages) {
        this.failMessages = failMessages;
    }
}
