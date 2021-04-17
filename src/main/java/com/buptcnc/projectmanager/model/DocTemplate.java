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
@TableName("t_doc_template")
public class DocTemplate {
    @TableId(type = IdType.AUTO)
    private String id;
    private String type;
    private Date expire;
    private String profId;
    private String docPath; // 后端存储全路径或相对路径，前端显示路径末尾的文件名
}
