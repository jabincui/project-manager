package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.Prof;
import com.buptcnc.projectmanager.model.Stu;
import com.buptcnc.projectmanager.security_model.Role;
import com.buptcnc.projectmanager.security_model.User;
import com.buptcnc.projectmanager.service.ProfServiceImpl;
import com.buptcnc.projectmanager.service.RoleServiceImpl;
import com.buptcnc.projectmanager.service.StuServiceImpl;
import com.buptcnc.projectmanager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    StuServiceImpl stuService;
    @Autowired
    ProfServiceImpl profService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;

    @PostMapping("/stu")
    public String signupStu(@RequestBody Stu stu) {
        if (stu.getAccount() == null || stu.getName() == null || stu.getPassword() == null)
            return Constant.INVALID;
        QueryWrapper<Stu> sqw = new QueryWrapper<>();
        sqw.eq("account", stu.getAccount());
        if (stuService.getOne(sqw) != null)
            return Constant.EXIST;
        stuService.save(stu);
        userService.save(new User(stu.getAccount(), stu.getPassword()));
        roleService.save(new Role(stu.getAccount(), Constant.ROLE_USER));
        return Constant.PASS;
    }

    @PostMapping("/prof")
    public String signupProf(@RequestBody Prof prof) {
        if (prof.getAccount() == null || prof.getName() == null || prof.getPassword() == null)
            return Constant.INVALID;
        QueryWrapper<Prof> pqw = new QueryWrapper<>();
        pqw.eq("account", prof.getAccount());
        if (profService.getOne(pqw) != null)
            return Constant.EXIST;
        profService.save(prof);
        userService.save(new User(prof.getAccount(), prof.getPassword()));
        roleService.save(new Role(prof.getAccount(), Constant.ROLE_USER));
        return Constant.PASS;
    }
}
