package com.zjut.Dicom.utils;

import java.io.File;

public class ContentVariable {
    // 存放dcm图像的目录   rootDir = D:\JavaSE\dicomPro\dicom_images
    public static final String rootDir = new File(System.getProperty("user.dir")).getParent()+File.separator+"dicom_images";

}
