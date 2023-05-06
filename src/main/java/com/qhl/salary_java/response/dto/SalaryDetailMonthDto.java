package com.qhl.salary_java.response.dto;

import lombok.Data;

import java.util.List;

/**
 * @author qhl
 * @date 2023-5-5 11:51
 */
@Data
public class SalaryDetailMonthDto {
    private String title;

    private Integer type;

    private String content;

    private List<SalaryDetailMonthDto> list;

}
