package com.qhl.salary_java.controller;

import com.qhl.salary_java.request.common.SingleIdVo;
import com.qhl.salary_java.request.salary.UserDetailInfoPageVo;
import com.qhl.salary_java.request.salary.UserSalaryDetailVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.service.UserSalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qhl
 * @date 2023/4/24 17:23
 */
@RestController
@Slf4j
@RequestMapping("/api/user/salary")
public class UserSalaryController {
    @Autowired
    private UserSalaryService service;

    @PostMapping("/detail/add")
    public WebApiResponse detailAdd(@RequestBody @Valid UserSalaryDetailVo vo) {
        return service.add(vo);
    }

    @PostMapping("/detail/infos")
    public WebApiResponse detailInfos(@RequestBody @Valid UserDetailInfoPageVo vo) {
        return service.detailInfos(vo);
    }


    /**
     * 获取用户某月具体的工资条信息
     */
    @PostMapping("/detail/month")
    public WebApiResponse detailMonth(@RequestBody @Valid SingleIdVo vo) {
        return service.detailMonth(vo.getId());
    }

}
