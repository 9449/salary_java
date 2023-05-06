package com.qhl.salary_java.controller;

import com.qhl.salary_java.request.common.SingleIdVo;
import com.qhl.salary_java.request.salary.template.SalaryTemplateChangeVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.service.SalaryTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qhl
 * @date 2023/4/24 11:25
 */
@RestController
@Slf4j
@RequestMapping("/api/salary/template")
public class SalaryTemplateController {
    @Autowired
    private SalaryTemplateService service;

    @PostMapping("/list")
    public WebApiResponse list() {
        return service.list();
    }

    @PostMapping("/change")
    public WebApiResponse change(@RequestBody @Valid SalaryTemplateChangeVo vo) {
        return service.change(vo);
    }
}
