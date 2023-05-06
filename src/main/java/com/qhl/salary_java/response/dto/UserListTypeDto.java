package com.qhl.salary_java.response.dto;

import lombok.Data;

/**
 * @author qhl
 * @date 2023/4/24 15:30
 */
@Data
public class UserListTypeDto {
    private Long id;

    /**
     * 账号
     */
    private String account;
}
