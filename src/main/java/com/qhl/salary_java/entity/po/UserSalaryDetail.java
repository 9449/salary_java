package com.qhl.salary_java.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author qhl
 * @date 2023/4/24 17:19
 */
@TableName(value = "user_salary_detail")
@Data
@ToString
public class UserSalaryDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

//    /**
//     * 用户账号
//     */
//    private String account;

    /**
     * 时间 年月
     */
    private String date;

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 具体的内容
     */
    private String content;

    /**
     * 逻辑删除位
     */
    private Long isDelete;
}
