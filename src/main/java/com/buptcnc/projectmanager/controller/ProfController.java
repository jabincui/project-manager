package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.Prof;
import com.buptcnc.projectmanager.security_model.User;
import com.buptcnc.projectmanager.service.ProfServiceImpl;
import com.buptcnc.projectmanager.service.RoleServiceImpl;
import com.buptcnc.projectmanager.service.UserServiceImpl;
import com.buptcnc.projectmanager.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/prof")
public class ProfController {
    @Autowired
    ProfServiceImpl profService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;


    @GetMapping("/identity")
    public Prof whoami(@RequestHeader String Authorization) {
        String token = Authorization.substring(JwtTokenUtil.TOKEN_PREFIX.length());
        log.info("token: " + token);
        if (JwtTokenUtil.checkJWT(token) == null) {
            log.info("error occurred when checking token");
            return null;
        }
        if (JwtTokenUtil.isExpiration(token)) {
            log.info("token expiration");
            return null;
        }
        String account = JwtTokenUtil.getUsername(token);
        log.info("account: " + account);
        QueryWrapper<Prof> pqw = new QueryWrapper<>();
        pqw.eq("account", account);
        Prof prof = profService.getOne(pqw);
        log.info("prof: " + prof.toString());
        return prof;
    }

    /**
     * WARNING 存在安全风险
     */
    @GetMapping("/show_all")
    public List<Prof> showAll() {
        return profService.list();
    }

    /**
     * return null if id not exist
     */
    @GetMapping("/select_by_id")
    public Prof selectById(@RequestParam String id) {
        QueryWrapper<Prof> pqw = new QueryWrapper<>();
        pqw.eq("account", id);
        return profService.getOne(pqw);
    }

    @PostMapping("/update")
    public String update(@RequestBody Prof prof) {
        if (prof.getAccount() == null) return Constant.INVALID;
        UpdateWrapper<Prof> puw = new UpdateWrapper<>();
        puw.eq("account", prof.getAccount());
        if (prof.getName() != null)
            puw.set("name", prof.getName());
        if (prof.getPassword() != null){
            puw.set("password", prof.getPassword());
            UpdateWrapper<User> uuw = new UpdateWrapper<>();
            uuw.eq("account", prof.getAccount());
            uuw.set("password", prof.getPassword());
            userService.update(uuw);
        }
        profService.update(puw);
        return Constant.PASS;
    }


    @PostMapping("/delete")
    public String delete(@RequestBody Prof prof) {
        if (prof.getAccount() == null) return Constant.INVALID;
        QueryWrapper<Prof> pqw = new QueryWrapper<>();
        pqw.eq("account", prof.getAccount());
        profService.remove(pqw);
        return Constant.PASS;
    }
}
