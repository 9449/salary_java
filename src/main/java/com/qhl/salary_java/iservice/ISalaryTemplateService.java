package com.qhl.salary_java.iservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qhl.salary_java.entity.po.SalaryTemplate;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 11:31
 */

public interface ISalaryTemplateService extends IService<SalaryTemplate> {
    List<SalaryTemplate> findByPid(long l);
}
