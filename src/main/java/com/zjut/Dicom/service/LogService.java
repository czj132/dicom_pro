package com.zjut.Dicom.service;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    /**
     * 插入日志记录
     * @param logType
     * @param userId
     * @param userName
     * @param userType
     * @param request
     * @return
     */
    int insertLog(String logType, Integer userId, String userName, Byte userType,
                  HttpServletRequest request);
}
