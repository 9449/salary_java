package com.qhl.salary_java.request.common;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 2019-05-06
 *
 * @author noxus
 */
@Data
@ToString
public class PageVO {
    @NotNull(message = "pageSize can not be null")
    private Integer pageSize;

    @NotNull(message = "pageNum can not be null")
    private Integer pageNum;
}
