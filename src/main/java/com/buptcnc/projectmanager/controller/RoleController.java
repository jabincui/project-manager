package com.buptcnc.projectmanager.controller;

import com.buptcnc.projectmanager.security_model.Role;
import com.buptcnc.projectmanager.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/show_all")
    public List<Role> listAll() {
        return roleService.list();
    }
}
