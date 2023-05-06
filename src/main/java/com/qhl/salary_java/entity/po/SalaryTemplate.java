package com.qhl.salary_java.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;


/**
 * @author qhl
 * @date 2023/4/24 11:21
 */
@TableName(value = "salary_template")
@Data
@ToString
public class SalaryTemplate {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父id 根目录为0
     */
    private Long pid;

    /**
     * 标题
     */
    private String title;

    /**
     * 0 基本 1 + 2 - 3 有子项
     */
    private Integer type;
}
