package com.qhl.salary_java.iservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhl.salary_java.entity.po.SalaryTemplate;
import com.qhl.salary_java.iservice.ISalaryTemplateService;
import com.qhl.salary_java.mapper.SalaryTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 11:31
 */

@Service
public class ISalaryTemplateServiceImpl extends ServiceImpl<SalaryTemplateMapper, SalaryTemplate> implements ISalaryTemplateService {
    @Autowired
    private SalaryTemplateMapper templateMapper;


    @Override
    public List<SalaryTemplate> findByPid(long pid) {
        return lambdaQuery().eq(SalaryTemplate::getPid,pid).list();
    }
}
