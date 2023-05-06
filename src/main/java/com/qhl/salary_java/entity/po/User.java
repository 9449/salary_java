package com.qhl.salary_java.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author qhl
 * @date 2023/4/21 15:31
 */
@TableName(value = "user")
@Data
@ToString
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 人员类型 0 员工 1 管理员
     */
    private Integer type;

    /**
     * 返回前端的token
     */
    private String token;

    /**
     * 微信唯一凭证
     */
    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
