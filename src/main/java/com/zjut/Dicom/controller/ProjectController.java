package com.zjut.Dicom.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import com.zjut.Dicom.service.ProjectService;
import com.zjut.Dicom.utils.responseResult.Result;
import com.zjut.Dicom.utils.responseResult.ResultGenerator;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Result save(@RequestBody Project project){
        int result = projectService.save(project);
        if (result == 0){
            //失败
            return ResultGenerator.getFailResult("创建失败");
        }else {
            //成功
            return ResultGenerator.getSuccessResult("创建成功");
        }
    }

    @PostMapping("/deleteProject")
    public boolean deleteById(@RequestParam(value = "id") Integer id){
        return projectService.deleteById(id);
    }

    @PutMapping
    public Result update(@RequestBody Project project){
        int result = projectService.update(project);
        if(result == 0){
            return ResultGenerator.getFailResult("修改失败");
        }else {
            return ResultGenerator.getSuccessResult("修改成功");
        }
    }


    @GetMapping
    public List<Project> getAll(){
        return projectService.getAll();
    }

    /**
     * 分页
     * @param begin:起始页数
     * @param size:显示页数
     *
     */
    @GetMapping("/{begin}/{size}")
    public PageInfo<Project> findAllByPages(@PathVariable("begin") int begin, @PathVariable("size") int size){
        return projectService.findAllByPages(begin, size);
    }
   /* @GetMapping("/selectByPage/{begin}/{size}")
    public List<Project> selectByPage(@PathVariable("begin") int begin, @PathVariable("size")int size){
        return projectService.selectByPage(begin, size);
    }*/

    /**
     * 质疑处理
     * @param studyId
     * @return
     */
    @GetMapping("/getVoByStuId/{studyId}")
    public PatientStudyVO getVoByStuId(@PathVariable Integer studyId){
        return projectService.getVoByStuId(studyId);
    }
}
