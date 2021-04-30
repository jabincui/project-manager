package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.RoleMapper;
import com.buptcnc.projectmanager.security_model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
}
