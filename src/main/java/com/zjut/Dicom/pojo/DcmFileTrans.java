package com.zjut.Dicom.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DcmFileTrans {
    // 患者id
    private Integer patientId;
    // 阶段数
    private Integer viewpoint;
    // 阶段Id
    private Integer studyId;
    /**
     * SeriesId 序列id，如果不为null，则覆盖之前上传的内容
     */
    private Integer seriesId;

    private MultipartFile[] files;
}
