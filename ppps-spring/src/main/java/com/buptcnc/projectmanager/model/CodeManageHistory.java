package com.buptcnc.projectmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_code_manage_history")
public class CodeManageHistory {
    @TableId(type = IdType.AUTO)
    private String id;
    private String docId; // projId
    private String managerId;
    private Date start;
    private Date end;

}
