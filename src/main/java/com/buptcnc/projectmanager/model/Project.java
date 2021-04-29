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
@TableName("t_project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String cooperation;
    private String prop;
    private String state;
    private Date start;
    private Date end;
    private String profId;
    private String remark;
    private String code;
    private String codeCopy;
    private String codeState;
}