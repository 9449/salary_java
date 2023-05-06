package com.qhl.salary_java.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author qhl
 * @date 2023/4/24 13:41
 */
@TableName(value = "salary_template_shield")
@Data
@ToString
public class SalaryTemplateShield {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 不展示的id
     */
    private Long templateId;
}
