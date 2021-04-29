package com.buptcnc.projectmanager.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.security_model.Role;
import com.buptcnc.projectmanager.security_model.User;
import com.buptcnc.projectmanager.service.RoleServiceImpl;
import com.buptcnc.projectmanager.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername:" + s);
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        QueryWrapper<User> uwq = new QueryWrapper<>();
        uwq.eq("username", s);
        User user = userService.getOne(uwq);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        QueryWrapper<Role> rqw = new QueryWrapper<>();
        rqw.eq("username", s);
        for (Role role : roleService.list(rqw)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(),"{noop}"+user.getPassword(),authorities);
    }
}
