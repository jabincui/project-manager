package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.ProfMapper;
import com.buptcnc.projectmanager.model.Prof;
import org.springframework.stereotype.Service;

@Service
public class ProfServiceImpl extends ServiceImpl<ProfMapper, Prof>
        implements ProfService {
}
