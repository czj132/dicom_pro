package com.zjut.Dicom.utils.responseResult;


/**
 * 响应结果生成工具
 */

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    // 只返回状态
    public static Result getSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }
    
    // 成功返回数据
    public static Result getSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    // 失败 
    public static Result getFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    /**
     * 根据传入的boolean值决定返回的状态
     * @param flag 标志，决定返回的是成功还是失败对象
     * @param msg 要返回的描述信息
     * @return
     */
    public static Result getResultByBoolean(boolean flag, String msg){
        if(flag){
            return new Result().setCode(ResultCode.SUCCESS)
                    .setMessage(msg);
        }else{
            return new Result().setCode(ResultCode.FAIL)
                    .setMessage(msg);
        }
    }
}

