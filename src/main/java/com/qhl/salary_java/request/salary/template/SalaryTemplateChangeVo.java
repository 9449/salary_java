package com.qhl.salary_java.request.salary.template;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qhl
 * @date 2023-5-5 11:17
 */
@Data
public class SalaryTemplateChangeVo {

    @NotNull
    private Long id;

    @NotNull
    private boolean checked;
}
