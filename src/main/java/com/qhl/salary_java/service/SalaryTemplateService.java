package com.qhl.salary_java.service;

import com.qhl.salary_java.entity.po.SalaryTemplate;
import com.qhl.salary_java.entity.po.SalaryTemplateShield;
import com.qhl.salary_java.iservice.ISalaryTemplateService;
import com.qhl.salary_java.iservice.ISalaryTemplateShieldService;
import com.qhl.salary_java.iservice.impl.ISalaryTemplateServiceImpl;
import com.qhl.salary_java.iservice.impl.ISalaryTemplateShieldServiceImpl;
import com.qhl.salary_java.request.salary.template.SalaryTemplateChangeVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.response.dto.SalaryTemplateListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qhl
 * @date 2023/4/24 11:25
 */
@Service
public class SalaryTemplateService {
    @Autowired
    private ISalaryTemplateService iSalaryTemplateService;
    @Autowired
    private ISalaryTemplateShieldService templateShieldService;


    public WebApiResponse list() {
        List<SalaryTemplate> pSalaryTemplates = iSalaryTemplateService.findByPid(0L);
        List<SalaryTemplateListDto> dtos = new ArrayList<>();
        List<Long> templateIds = templateShieldService.list().stream().map(SalaryTemplateShield::getTemplateId).collect(Collectors.toList());
        dtos = pSalaryTemplates.stream().map(item -> {
            SalaryTemplateListDto dto = new SalaryTemplateListDto();
            BeanUtils.copyProperties(item, dto);
            if (item.getType() == 3) {
                List<SalaryTemplate> sons = iSalaryTemplateService.findByPid(item.getId());
                dto.setList(sons.stream().map(i -> {
                    SalaryTemplateListDto sonDto = new SalaryTemplateListDto();
                    BeanUtils.copyProperties(i, sonDto);
                    sonDto.setChecked(!templateIds.contains(i.getId()));
                    return sonDto;
                }).collect(Collectors.toList()));
            }
            dto.setChecked(!templateIds.contains(item.getId()));
            return dto;
        }).collect(Collectors.toList());
        return WebApiResponse.success(dtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public WebApiResponse change(SalaryTemplateChangeVo vo) {
        SalaryTemplate salaryTemplate = iSalaryTemplateService.getById(vo.getId());
        if (salaryTemplate == null) {
            return WebApiResponse.error("INFO NOT FOUND");
        }
        SalaryTemplateShield shieldInfo = templateShieldService.findByTemplateId(vo.getId());
        if (shieldInfo == null && !vo.isChecked()) {
            SalaryTemplateShield addShield = new SalaryTemplateShield();
            addShield.setTemplateId(vo.getId());
            templateShieldService.save(addShield);
        } else {
            templateShieldService.removeById(shieldInfo);
        }
        return WebApiResponse.success("操作成功");
    }
}
