package com.zjut.Dicom.pojo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class StudyDcmImageVO {
    private Integer studyId;
    private Map<Integer, List<String>> seriesInfo;

    public StudyDcmImageVO(Integer studyId){
        this.studyId = studyId;
        this.seriesInfo = new HashMap<>();
    }
}
