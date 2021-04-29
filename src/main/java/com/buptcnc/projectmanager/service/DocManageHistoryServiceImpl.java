package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.DocManageHistoryMapper;
import com.buptcnc.projectmanager.model.DocManageHistory;
import org.springframework.stereotype.Service;

@Service
public class DocManageHistoryServiceImpl
        extends ServiceImpl<DocManageHistoryMapper, DocManageHistory>
        implements DocManageHistoryService {
}
