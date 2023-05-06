package com.qhl.salary_java.response.dto;

import lombok.Data;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/24 11:27
 */
@Data
public class SalaryTemplateListDto {
    private Long id;

    private Long pid;

    private String title;

    private Integer type;

    private boolean checked;

    private List<SalaryTemplateListDto> list;
}
