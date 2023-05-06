package com.qhl.salary_java.request.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author qhl
 * @date 2023/4/21 16:00
 */
@Data
public class UserLoginVo {
    /**
     * 账号
     */
    @NotEmpty
    private String account;

    /**
     * 密码
     */
    @NotEmpty
    private String password;
}
