package com.zjut.Dicom.service;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    /**
     * 插入日志记录
     * @param logType 日志类型
     * @param userId 用户id
     * @param userName 用户名
     * @param userType 用户类型
     * @param request 请求

     */
    int insertLog(String logType, Integer userId, String userName, Byte userType,
                  HttpServletRequest request);
}
