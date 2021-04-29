package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.DocMapper;
import com.buptcnc.projectmanager.model.Doc;
import org.springframework.stereotype.Service;

@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
        implements DocService {
}
