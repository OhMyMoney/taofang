package com.taofang.webapi.util;

import com.taofang.webapi.result.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class ResultUtil {
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = 1;

    public static Result successResult(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result failResult(String failMessage){
        List<String> failMessageList = Arrays.asList(failMessage);
        return new Result(FAIL_CODE, failMessageList);
    }

    public static Result failResult(List<String> failMessageList){
        return new Result(FAIL_CODE, failMessageList);
    }
}
