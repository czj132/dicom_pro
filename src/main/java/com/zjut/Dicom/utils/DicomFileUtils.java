package com.zjut.Dicom.utils;

import com.zjut.Dicom.config.PathConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DicomFileUtils {

    /**
     * 将上传的dicom图像转换为nii格式
     * @param dcmDir
     * @param niiDir
     * @return
     */
    public static boolean convertToNii(File dcmDir, File niiDir){
        String convertToNii = PathConfig.convertToNii;
        String[] runString = new String[]{
                PathConfig.niiPythonEnv,
                convertToNii,
                // 序列目录
                dcmDir.toString(),
                // 输出的nii目录， nii文件名为序列的目录名
                niiDir.toString()
        };
        try {
            Process process = Runtime.getRuntime().exec(runString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            reader.close();
            int code = process.waitFor();
            if (code != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((line = errorReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println(result);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
