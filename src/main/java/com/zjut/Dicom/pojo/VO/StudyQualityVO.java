package com.zjut.Dicom.pojo.VO;

import com.zjut.Dicom.pojo.Series;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudyQualityVO {
    //阶段表
    private Integer studyId;
    private Integer projectId;
    private Byte viewpoint;
    private Date uploadTime;
    private Date planTime;
    private Byte status;

    //项目表
    private String projectName;
    private Integer patientId;
    private Integer viewTotal;

    //病人表
    private String hospitalSource;
    private String part;
    private String examinationType;

}
