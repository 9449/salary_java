package com.qhl.salary_java.security;

import lombok.Data;
import lombok.ToString;

/**
 * @author noxus
 */
@Data
@ToString
public class LoginUserInfo {

    private Long uid;

    private String token;

    private String username;

    private String password;

    private Long unitId;

    private String producerCode;

    private Integer delFlag;

    private String expireTime;
}
