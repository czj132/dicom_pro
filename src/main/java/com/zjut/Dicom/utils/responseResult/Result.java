package com.zjut.Dicom.utils.responseResult;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message = "success";
    private Object data;

    // 后面result生成器需要以下方法
    public Result setCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }
}
