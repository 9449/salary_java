package com.qhl.salary_java.controller;

import com.qhl.salary_java.request.common.SingleIdVo;
import com.qhl.salary_java.request.user.UserAddVo;
import com.qhl.salary_java.request.user.UserLoginVo;
import com.qhl.salary_java.request.user.UserPageVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.security.UserStatus;
import com.qhl.salary_java.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author qhl
 * @date 2023/4/21 15:46
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 登陆
     * @param vo
     * @return
     */
    @PostMapping("/login")
    public WebApiResponse login(@RequestBody @Valid UserLoginVo vo) {
        return service.login(vo);
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping("")
    public WebApiResponse getCurrentUserDetail() {
        Long uid = UserStatus.getUserInfo().getUid();
        return service.getCurrentUserDetail(uid);
    }

    /**
     * 分页查询用户
     * @param vo
     * @return
     */
    @PostMapping("/search/page")
    public WebApiResponse page(@RequestBody @Valid UserPageVo vo) {
        return service.page(vo);
    }

    @PostMapping("/list/type")
    public WebApiResponse listType() {
        return service.listType();
    }

    /**
     * 新增用户
     * @param vo
     * @return
     */
    @PostMapping("/add")
    public WebApiResponse add(@RequestBody @Valid UserAddVo vo) {
        return service.add(vo);
    }

    @PostMapping("/delete")
    public WebApiResponse delete(@RequestBody @Valid SingleIdVo vo) {
        return service.delete(vo.getId());
    }
}
