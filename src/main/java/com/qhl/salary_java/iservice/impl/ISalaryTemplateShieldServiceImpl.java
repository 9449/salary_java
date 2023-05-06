package com.qhl.salary_java.iservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhl.salary_java.entity.po.SalaryTemplateShield;
import com.qhl.salary_java.iservice.ISalaryTemplateShieldService;
import com.qhl.salary_java.mapper.SalaryTemplateShieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author qhl
 * @date 2023/4/28 11:39
 */
@Service
public class ISalaryTemplateShieldServiceImpl extends ServiceImpl<SalaryTemplateShieldMapper, SalaryTemplateShield> implements ISalaryTemplateShieldService {
    @Autowired
    private SalaryTemplateShieldMapper mapper;
    @Override
    public SalaryTemplateShield findByTemplateId(Long templateId) {
        return lambdaQuery().eq(SalaryTemplateShield::getTemplateId,templateId).one();
    }
}
