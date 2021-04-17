package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.Prof;
import com.buptcnc.projectmanager.model.Project;
import com.buptcnc.projectmanager.model.Stu;
import com.buptcnc.projectmanager.service.ManagerServiceImpl;
import com.buptcnc.projectmanager.service.ProfServiceImpl;
import com.buptcnc.projectmanager.service.ProjectServiceImpl;
import com.buptcnc.projectmanager.service.StuServiceImpl;
import com.buptcnc.projectmanager.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/proj")
public class ProjectController {

    @Autowired
    ProfServiceImpl profService;
    @Autowired
    StuServiceImpl stuService;
    @Autowired
    ProjectServiceImpl projService;
    @Autowired
    ManagerServiceImpl managerService;


    @GetMapping("/allprojects")
    public List<Project> getAllProjects() {
        return projService.list();
    }


    /**
     * 获取和用户自己有关的项目
     * @param request
     * @return
     */
    @GetMapping(value = "/myprojects")
    public List<Project> getProjects(HttpServletRequest request) {
        String token =  request.getHeader("Authorization");
        String username = JwtTokenUtil.getUsername(token);

        QueryWrapper<Prof> profQueryWrapper = new QueryWrapper<>();
        profQueryWrapper.eq("account", username);
        Prof prof = profService.getOne(profQueryWrapper);
        if (prof != null) {
            QueryWrapper<Project> projQueryWrapper = new QueryWrapper<>();
            projQueryWrapper.eq("prof", username);
            return projService.list(projQueryWrapper);
        }

        QueryWrapper<Stu> stuQueryWrapper = new QueryWrapper<>();
        stuQueryWrapper.eq("account", username);
        Stu stu = stuService.getOne(stuQueryWrapper);
        if (stu != null) {
            QueryWrapper<Project> projQueryWrapper = new QueryWrapper<>();
            projQueryWrapper.eq("prof", username);
            return projService.list(projQueryWrapper);

        }
        return null;
    }


    /**
     * 不建议在此处提交的字段：end code codeCopy codeState
     * 禁止的字段值： state = DONE
     * @param project
     * @return
     */
    @PostMapping("/add_proj")
    public String insertProj(@RequestBody Project project) {
        log.info(project.toString());
        if (project.getState() == null || project.getState().equals(Constant.STA_DONE)
                || project.getName() == null || project.getCooperation() == null
                || project.getProp() == null || project.getStart() == null
                || project.getRemark() == null) {
            return "INVALID_BODY";
        }
        project.setEnd(null);
        project.setCode(null);
        project.setCodeCopy(null);
        project.setCodeState(null);
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.eq("cooperation", project.getCooperation())
                .eq("prop", project.getProp())
                .eq("name", project.getName());
        Project tmp = projService.getOne(projectQueryWrapper);
        if (tmp != null) {
            return Constant.EXIST;
        }
//        UpdateWrapper<Project> projectUpdateWrapper = new UpdateWrapper<>();
//        projectUpdateWrapper
//                .set("name", project.getName());

        projService.save(project);
        return Constant.PASS;
    }


    /**
     * 不可更新字段： profId
     * 此处不可更新字段： state end codeState
     * 建议的前端数据检查： 更新code_copy前保证code有值
     * @param project
     * @return
     */
    @PostMapping("/update_proj")
    public String updateProj(@RequestBody Project project) {
        if (project.getId() == null) {
            return Constant.INVALID;
        }
        UpdateWrapper<Project> puw = new UpdateWrapper<>();
        puw.eq("id", project.getId());
        if (project.getName() != null) puw.set("name", project.getName());
        if (project.getCooperation() != null)
            puw.set("cooperation", project.getCooperation());
        if (project.getProp() != null) puw.set("prop", project.getProp());
        if (project.getRemark() != null) puw.set("remark", project.getRemark());
        if (project.getCode() != null) {
            puw.set("code", project.getCode());
            puw.set("code_state", Constant.STA_DONE);
        }
        if (project.getCodeCopy() != null) {
            puw.set("code_copy", project.getCodeCopy());
            puw.set("code_state", Constant.STA_DONE);
        }
        projService.update(puw);
        return Constant.PASS;
    }

    @PostMapping("/end_proj")
    public String endProj(@RequestBody Project project) {
        if (project.getId() == null || project.getEnd() == null) {
            return Constant.INVALID;
        }
        UpdateWrapper<Project> projectUpdateWrapper = new UpdateWrapper<>();
        projectUpdateWrapper.eq("id", project.getId())
                .set("state", Constant.STA_DONE)
                .set("end", project.getEnd());

        return Constant.PASS;
    }

}