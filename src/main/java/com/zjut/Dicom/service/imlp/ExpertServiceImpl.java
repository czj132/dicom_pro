package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.ExpertMapper;
import com.zjut.Dicom.pojo.VO.ExpertPreviewVO;
import com.zjut.Dicom.service.ExpertService;
import com.zjut.Dicom.utils.converts.ExpertInfoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private ExpertMapper expertMapper;

    @Override
    public ExpertPreviewVO queryExpertInfoById(Integer expertId) {
        return ExpertInfoConvert.convertExpertInfo(expertMapper.getById(expertId));
    }
}
