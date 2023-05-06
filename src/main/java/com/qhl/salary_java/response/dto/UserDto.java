package com.qhl.salary_java.response.dto;

import lombok.Data;

/**
 * @author qhl
 * @date 2023/4/21 16:52
 */

@Data
public class UserDto {
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
     * 人员类型 0 员工 1 管理员
     */
    private Integer type;


}
