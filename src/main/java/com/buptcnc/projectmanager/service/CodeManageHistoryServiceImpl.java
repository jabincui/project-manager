package com.buptcnc.projectmanager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptcnc.projectmanager.mapper.CodeManageHistoryMapper;
import com.buptcnc.projectmanager.model.CodeManageHistory;
import org.springframework.stereotype.Service;

@Service
public class CodeManageHistoryServiceImpl
        extends ServiceImpl<CodeManageHistoryMapper, CodeManageHistory>
        implements CodeManageHistoryService {
}
