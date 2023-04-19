package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExpert {
    private Integer id;
    private Integer projectId;
    private Integer expertId;
    private Byte expertType;
}
