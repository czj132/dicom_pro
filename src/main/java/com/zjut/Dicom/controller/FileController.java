package com.zjut.Dicom.controller;

import com.alibaba.fastjson.JSON;
import com.zjut.Dicom.config.PathConfig;
import com.zjut.Dicom.service.ExpertService;
import com.zjut.Dicom.service.LogService;
import com.zjut.Dicom.utils.responseResult.Result;
import com.zjut.Dicom.utils.responseResult.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private ExpertService expertService;

    @Autowired
    private LogService logService;

    private String DCM_IMAGES_ROOT_DIR = PathConfig.DCM_IMAGES_ROOT_DIR;
    private String LABEL_DIR = PathConfig.LABEL_DIR;

    private static String readFileToEnd(File file) throws IOException {
        final String encoding = "UTF-8";

        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream in = new FileInputStream(file);
        in.read(fileContent);
        in.close();

        return new String(fileContent, encoding);
    }

    @PostMapping("/uploading/label")
    public Result uploadLabel(@RequestBody Map<String, Object> arr, HttpServletRequest request) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String labelFileName = (String) arr.get("file_name");
        Integer currImgIdIndex = (Integer) arr.get("currImgIdIndex");
        Integer seriesId = (Integer) arr.get("series_id");
        Integer axios_index = (Integer) arr.get("axios_index");
        Integer expertId = (Integer) arr.get("expert_id");
        String toolType = (String) arr.get("toolType");
        String operationType = (String) arr.get("operationType");//add和delete
        System.out.println(arr.get("label_data"));
        List<Map<String, Object>> label_data = (List<Map<String, Object>>)arr.get("label_data");
        String fileName = "";
        String json = "";
        fileName =  currImgIdIndex +"_" + axios_index + "_" +expertId+ "_" +toolType+ ".json";
        json = JSON.toJSONString(label_data);
        File dir = new File(LABEL_DIR);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File series_dir = new File(LABEL_DIR+"\\"+seriesId);
        if(!series_dir.exists()){
            series_dir.mkdirs();
        }
        //label_data如果为空，直接保存为空。
        File file = new File(series_dir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
        bufferedWriter.write(json);
        bufferedWriter.close();
        Integer userId = expertId;
        String userName = expertService.queryExpertInfoById(userId).getName();
        String logType = "上传"+toolType+"标注";
        if("add".equals(operationType)){
            logType = "上传"+toolType+"标注";
        }else if("delete".equals(operationType)){
            logType = "删除"+toolType+"标注";
        }
        logService.insertLog(logType, userId, userName, (byte)1, request);
        return ResultGenerator.getSuccessResult("success");
    }

    @PostMapping(value="/download/label", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object downloadLabel(@RequestParam("currImgIdIndex") String currImgIdIndex,
                                @RequestParam("series_id") Integer series_id,
                                @RequestParam("axios_index") Integer axios_index,
                                @RequestParam("expert_id") Integer expert_id) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String[] extList = new String[]{"_angle.json", "_arrowAnnotate.json", "_ellipticalRoi.json",
                "_FreehandRoi.json", "_length.json", "_probe.json", "_rectangleRoi.json"};
        List<String> jsonList = new ArrayList<>();
        for(String str : extList){
            String uri = currImgIdIndex+"_"+axios_index+"_"+expert_id+str;
            String seriesDir = LABEL_DIR+"\\"+series_id;
            File file = new File(seriesDir, uri);
            if(file.exists()){
                String json = readFileToEnd(file);
                jsonList.add(json);
            }
        }
        return new ResponseEntity<Object>(jsonList, headers, HttpStatus.OK);
    }

}
