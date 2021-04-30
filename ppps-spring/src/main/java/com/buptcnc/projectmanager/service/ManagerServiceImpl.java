package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.ManagerMapper;
import com.buptcnc.projectmanager.model.Manager;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager>
        implements ManagerService {
}
