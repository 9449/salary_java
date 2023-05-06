package com.qhl.salary_java.iservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.request.user.UserPageVo;
import com.qhl.salary_java.response.dto.UserPageDto;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 10:42
 */

public interface IUserService extends IService<User> {


    User findByAccount(String account);

    IPage<UserPageDto> findByAccountLike(UserPageVo vo);

    List<User> findByType(Integer i);

    User findByToken(String token);

    List<User> findByAccounts(List<String> accounts);
}
