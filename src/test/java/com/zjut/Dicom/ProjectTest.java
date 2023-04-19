package com.zjut.Dicom;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import com.zjut.Dicom.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
public class ProjectTest {
    @Autowired
    private ProjectService projectService;
    @Test
    public void testGetId(){
        Project project = projectService.getById(1);
        System.out.println(project);
    }

    @Test
    public void testGetAll(){
        List<Project> projects = projectService.getAll();
        System.out.println(projects);
    }


    @Test
    public void testDelete(){
        boolean flag = projectService.deleteById(5);
        System.out.println(flag);
    }

    @Test
    public void testSave(){
        Project project = new Project();
        project.setName("测试项目665");
        project.setStatus((byte)2);
        project.setPharmaceutical("康恩贝");
        project.setMedicine("消化药");
        project.setDisease("消化饼");
        project.setDescription("项目还没建好");
        project.setViewTotal(5);
        project.setLogicDeletion((byte) 1);
        boolean ret = projectService.save(project);
        System.out.println(ret);
    }

    @Test
    public void testUpdate(){
        Project project = new Project();
        project.setName("我是测试项目哈哈哈");
        project.setPharmaceutical("康恩贝");
        project.setMedicine("消化药");
        project.setDisease("肠胃病");
        project.setDescription("我的项目还没建好哈哈哈哈");
        boolean ret = projectService.update(project);
        System.out.println(ret);
    }

    @Test
    public void getVoByStuId(){
        PatientStudyVO voByStuId = projectService.getVoByStuId(60);
        System.out.println(voByStuId);
    }

    @Test
    public void testSelectByPage(){
        List<Project> projects = projectService.selectByPage(1, 5);
        System.out.println(projects);
    }

    @Test
    public void testFindAllByPages(){
        PageInfo<Project> pages = projectService.findAllByPages(1, 5);
        System.out.println(pages);
    }

}
