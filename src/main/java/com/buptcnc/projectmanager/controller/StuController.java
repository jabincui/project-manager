package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.Stu;
import com.buptcnc.projectmanager.security_model.User;
import com.buptcnc.projectmanager.service.RoleServiceImpl;
import com.buptcnc.projectmanager.service.StuServiceImpl;
import com.buptcnc.projectmanager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    StuServiceImpl stuService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;

    /**
     * WARNING 存在安全风险
     */
    @GetMapping("/show_all")
    public List<Stu> showAll() {
        return stuService.list();
    }

    /**
     * return null if id not exist
     */
    @GetMapping("/select_by_id")
    public Stu selectById(@RequestParam String id) {
        QueryWrapper<Stu> sqw = new QueryWrapper<>();
        sqw.eq("account", id);
        return stuService.getOne(sqw);
    }

    @PostMapping("/update")
    public String update(@RequestBody Stu stu) {
        if (stu.getAccount() == null) return Constant.INVALID;
        UpdateWrapper<Stu> suw = new UpdateWrapper<>();
        suw.eq("account", stu.getAccount());
        if (stu.getName() != null)
            suw.set("name", stu.getName());
        if (stu.getPassword() != null){
            suw.set("password", stu.getPassword());
            UpdateWrapper<User> uuw = new UpdateWrapper<>();
            uuw.eq("account", stu.getAccount());
            uuw.set("password", stu.getPassword());
            userService.update(uuw);
        }
        stuService.update(suw);
        return Constant.PASS;
    }


    @PostMapping("/delete")
    public String delete(@RequestBody Stu stu) {
        if (stu.getAccount() == null) return Constant.INVALID;
        QueryWrapper<Stu> sqw = new QueryWrapper<>();
        sqw.eq("account", stu.getAccount());
        stuService.remove(sqw);
        return Constant.PASS;
    }

}