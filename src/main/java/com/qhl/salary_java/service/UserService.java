package com.qhl.salary_java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhl.salary_java.constants.ApiConstant;
import com.qhl.salary_java.iservice.IUserService;
import com.qhl.salary_java.iservice.impl.IUserServiceImpl;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.request.user.UserAddVo;
import com.qhl.salary_java.request.user.UserLoginVo;
import com.qhl.salary_java.request.user.UserPageVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.response.dto.UserDto;
import com.qhl.salary_java.response.dto.UserListTypeDto;
import com.qhl.salary_java.response.dto.UserPageDto;
import com.qhl.salary_java.utils.Base64Util;
import com.qhl.salary_java.utils.SnowflakeIdWorker;
import com.qhl.salary_java.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qhl
 * @date 2023/4/21 15:37
 */

@Service
@Slf4j
public class UserService {
    @Autowired
    private SnowflakeIdWorker idWorker;
    @Autowired
    private IUserService userService;

    public WebApiResponse login(UserLoginVo vo) {
        User user = userService.findByAccount(vo.getAccount());
        if (user == null) {
            return WebApiResponse.error("请输入正确账号和密码！！！");
        }
        try {
            if (!user.getPassword().equals(Base64Util.encryptBASE64((ApiConstant.key + vo.getPassword()).getBytes()))) {
                return WebApiResponse.error("请输入正确账号和密码！！！");
            }
        } catch (Exception e) {
            return WebApiResponse.error("请输入正确账号和密码！！！");
        }
        String token = TokenUtil.createToken(idWorker.nextId());
        user.setToken(token);
        userService.updateById(user);
        return WebApiResponse.success(token);
    }


    public WebApiResponse getCurrentUserDetail(Long uid) {
        User userInfo = userService.getById(uid);
        if (userInfo == null) {
            return WebApiResponse.error("INFO NOT FOUND!!!");
        }
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userInfo,dto);
        return WebApiResponse.success(dto);
    }

    public WebApiResponse page(UserPageVo vo) {
        IPage<UserPageDto> dtos = userService.findByAccountLike(vo);
        return WebApiResponse.success(dtos);
    }


    public WebApiResponse add(UserAddVo vo) {
        User user = userService.findByAccount(vo.getAccount());
        Date date = new Date();
        if (user != null) {
            return WebApiResponse.error("该账号已存在");
        }
        User addUser = new User();
        addUser.setCreatedTime(date);
        addUser.setUpdatedTime(date);
        addUser.setUsername(vo.getUsername());
        addUser.setAccount(vo.getAccount());
        addUser.setType(0);
        try {
            addUser.setPassword(Base64Util.encryptBASE64((ApiConstant.key + vo.getPassword()).getBytes()));
        } catch (Exception e) {
            return WebApiResponse.error("操作失败");
        }
        boolean save = userService.save(addUser);
        if (save) {
            return WebApiResponse.success("操作成功");
        }
        return WebApiResponse.error("操作失败");

    }

    public WebApiResponse delete(Long id) {
        User userInfo = userService.getById(id);
        if (userInfo == null) {
            return WebApiResponse.error("INFO NOT FOUND!!!");
        }
        boolean b = userService.removeById(userInfo);
        if (b) {
            return WebApiResponse.success("操作成功");
        }
        return WebApiResponse.error("操作失败");
    }

    public WebApiResponse listType() {
        List<User> users = userService.findByType(0);
        List<UserListTypeDto> dtos = new ArrayList<>();
        dtos = users.stream().map(i -> {
            UserListTypeDto dto = new UserListTypeDto();
            BeanUtils.copyProperties(i,dto);
            return dto;
        }).collect(Collectors.toList());
        return WebApiResponse.success(dtos);
    }
}
