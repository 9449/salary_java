package com.qhl.salary_java.request.salary;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author qhl
 * @date 2023/4/24 17:25
 */
@Data
public class UserSalaryDetailVo {
    @NotNull
    private Long uid;
    @NotEmpty
    private String date;
    @NotEmpty
    private String userSalaryDetail;
    @NotEmpty
    private String account;
}
