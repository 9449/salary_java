package com.qhl.salary_java.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qhl
 * @date 2023/4/28 15:08
 */

@Data
@ToString
@TableName(value = "user_detail_info")
public class UserDetailInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String account;

    private Long uid;

    @JsonFormat(pattern = "yyyy年MM月")
    private Date date;


    private BigDecimal realWages;

}
