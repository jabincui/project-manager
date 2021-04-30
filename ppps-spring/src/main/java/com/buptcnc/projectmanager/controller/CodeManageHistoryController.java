package com.buptcnc.projectmanager.controller;

import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.CodeManageHistory;
import com.buptcnc.projectmanager.model.Project;
import com.buptcnc.projectmanager.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class CodeManageHistoryController {


    @Autowired
    ProjectServiceImpl projectService;



    public String handOver(@RequestBody Project project,
                           @RequestHeader String successor) {
        // TODO: 2021/4/15
        if (project.getId() == null) {
            return Constant.INVALID;
        }




        return Constant.PASS;
    }
}
