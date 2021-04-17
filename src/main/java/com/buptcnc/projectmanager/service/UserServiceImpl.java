package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.UserMapper;
import com.buptcnc.projectmanager.security_model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}
