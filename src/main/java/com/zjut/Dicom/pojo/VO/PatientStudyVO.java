package com.zjut.Dicom.pojo.VO;

import com.zjut.Dicom.pojo.Study;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PatientStudyVO {
    //阶段表
    private Integer studyId;
    private Date uploadTime;
    private Date planTime;

    //项目表
    private String proName;

    //病人表
    private Integer patientId;
    private String hospitalSource;

    //质控表
    private Byte status;
    private String description;
}
