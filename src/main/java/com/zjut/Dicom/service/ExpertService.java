package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.VO.ExpertPreviewVO;

public interface ExpertService {

    /**
     * 查询专家个人信息
     * @param expertId
     * @return
     */
    ExpertPreviewVO queryExpertInfoById(Integer expertId);
}
