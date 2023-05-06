package com.qhl.salary_java.iservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qhl.salary_java.entity.po.SalaryTemplateShield;

/**
 * @author qhl
 * @date 2023/4/28 11:38
 */

public interface ISalaryTemplateShieldService extends IService<SalaryTemplateShield> {
    SalaryTemplateShield findByTemplateId(Long templateId);
}
