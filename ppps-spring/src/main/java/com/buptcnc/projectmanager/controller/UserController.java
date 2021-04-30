package com.buptcnc.projectmanager.controller;

import com.buptcnc.projectmanager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buptcnc.projectmanager.security_model.User;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/show_all")
    public List<User> listAll() {
        return userService.list();
    }
}
