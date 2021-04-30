package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.DocTemplateMapper;
import com.buptcnc.projectmanager.model.DocTemplate;
import org.springframework.stereotype.Service;

@Service
public class DocTemplateServiceImpl extends ServiceImpl<DocTemplateMapper, DocTemplate>
        implements DocTemplateService {
}
