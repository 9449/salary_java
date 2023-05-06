package com.qhl.salary_java.request.user;

import com.qhl.salary_java.request.common.PageVO;
import lombok.Data;

/**
 * @author qhl
 * @date 2023/4/21 17:44
 */
@Data
public class UserPageVo extends PageVO {
    private String account;
}
