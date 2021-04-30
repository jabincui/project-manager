package com.buptcnc.projectmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_doc")
public class Doc {
    @TableId(type = IdType.AUTO)
    private String id;
    private String name;
    private String state;
    private String template;
    private String format;
    private String docPath;
    private String worker;
    private String manager;
    private String proj;
}
