package com.zjut.Dicom.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DcmFileReTrans {
    private Integer patientId;
    // 阶段Id
    private Integer studyId;
    // 直接上传多级目录
    private MultipartFile[] files;
}
