package com.zjut.Dicom.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import com.zjut.Dicom.service.ProjectService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public boolean save(@RequestBody Project project){
        return projectService.save(project);
    }

    @PostMapping("/deleteProject")
    public boolean deleteById(@RequestParam(value = "id") Integer id){
        return projectService.deleteById(id);
    }

    @PutMapping
    public boolean update(@RequestBody Project project){
        return projectService.update(project);
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
