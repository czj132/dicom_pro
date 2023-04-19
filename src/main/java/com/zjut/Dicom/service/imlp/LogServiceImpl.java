package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.LogMapper;
import com.zjut.Dicom.pojo.Log;
import com.zjut.Dicom.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LogServiceImpl implements LogService {


    @Autowired
    private LogMapper logMapper;

    private static final String logFormat = "%s在 %s 于ip: %s 进行%s";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 类型  0-管理员  1-专家  2-医院  3-质控人员  4-报告人员
    @Override
    public int insertLog(String logType, Integer userId, String userName, Byte userType, HttpServletRequest request) {
        Log log = new Log();
        log.setOperationType(logType);
        log.setOperationTime(new Date());
        log.setUserId(userId);
        log.setUserType(userType);
        log.setDescribe(String.format(logFormat, userName, simpleDateFormat.format(log.getOperationTime()),
                request.getRemoteAddr(), log.getOperationType()));
        return logMapper.save(log);
    }
}
