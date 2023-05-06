package com.qhl.salary_java.request.salary;

import com.qhl.salary_java.request.common.PageVO;
import lombok.Data;


/**
 * @author qhl
 * @date 2023/4/28 15:15
 */
@Data
public class UserDetailInfoPageVo extends PageVO {
    private Long uid;


    private String key;
}
