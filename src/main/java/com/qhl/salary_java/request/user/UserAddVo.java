package com.qhl.salary_java.request.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author qhl
 * @date 2023/4/22 16:44
 */

@Data
public class UserAddVo {
    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户账号
     */
    @NotEmpty
    private String account;

    /**
     * 用户密码
     */
    @NotEmpty
    private String password;
}
