package com.buptcnc.projectmanager.security_model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录需要USER身份，业务中需要判定身份时，用账号所在的表判断
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String name;

    public Role(String username, String name) {
        this.username = username;
        this.name = name;
    }
}
