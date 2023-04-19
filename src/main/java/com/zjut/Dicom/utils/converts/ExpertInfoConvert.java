package com.zjut.Dicom.utils.converts;

import com.zjut.Dicom.pojo.Expert;
import com.zjut.Dicom.pojo.VO.ExpertPreviewVO;
import org.springframework.beans.BeanUtils;

public class ExpertInfoConvert {

    public static ExpertPreviewVO convertExpertInfo(Expert sourceObj){
        if(null!=sourceObj){
            ExpertPreviewVO targetObj = new ExpertPreviewVO();
            BeanUtils.copyProperties(sourceObj, targetObj);
            return targetObj;
        }else{
            return null;
        }
    }
}
