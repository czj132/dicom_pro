package com.zjut.Dicom.config;

import java.io.File;

public class PathConfig {
    //标记根目录
    public final static String LABEL_DIR =  getPath() + File.separator +"label_files";
    //DICOM文件图像根目录
    public final static String DCM_IMAGES_ROOT_DIR = getPath() + File.separator + "dicom_images";
    //资源文件目录
    public final static String RESOURCES_PATH = getPath() + File.separator + "resources_files";
    //电子签名存放路径
    public final static String eSignatureDir = getPath() + File.separator + "resources_files\\file\\eSignature";
    //python运行环境
    public final static String pythonEnv = getPath() + File.separator + "python_code\\python36\\python.exe";
    //    电子签名获取代码
    public final static String getESignature =  getPath() + File.separator + "python_code\\signature\\signature_change.py";
    // dicom转换为nii的代码
    public final static String convertToNii = getPath() + File.separator + "python_code\\dicom\\convert_nitfi.py";
    public final static String niiPythonEnv = getPath() + File.separator + "python_code\\dicom\\ct_dicom\\python.exe";

    public static String getPath(){
        String path = "";
        try {
            path = new File(System.getProperty("user.dir")).getParent();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(""+e);
        }
       return path;
    }
}
