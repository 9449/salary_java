package com.qhl.salary_java.response.dto;


import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.utils.Base64Util;
import lombok.Data;
import org.springframework.beans.BeanUtils;


/**
 * @author qhl
 * @date 2023/4/28 11:19
 */

@Data
public class UserPageDto {
    private Long id;

    /**
     * 用户名称
     */
    private String username;

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


    public static UserPageDto adapt(User user) {
        UserPageDto dto = new UserPageDto();
        BeanUtils.copyProperties(user,dto);
        dto.setPassword(validPsd(dto.getPassword()));
        return dto;
    }


    private static String validPsd(String password) {
        String res = "";
        try {
            res = new String(Base64Util.decryBASE64(password));
        } catch (Exception e) {

        }
        return res;
    }

}
