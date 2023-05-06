package com.qhl.salary_java.iservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.iservice.IUserService;
import com.qhl.salary_java.mapper.UserMapper;
import com.qhl.salary_java.request.user.UserPageVo;
import com.qhl.salary_java.response.dto.UserPageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author qhl
 * @date 2023/4/28 10:43
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByToken(String token) {
        return lambdaQuery().eq(User::getToken, token).one();
    }

    @Override
    public List<User> findByAccounts(List<String> accounts) {
        return lambdaQuery().in(User::getAccount,accounts).list();
    }

    @Override
    public User findByAccount(String account) {
        return lambdaQuery().eq(User::getAccount,account).one();
    }

    @Override
    public IPage<UserPageDto> findByAccountLike(UserPageVo vo) {
        IPage<User> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(vo.getAccount()), User::getAccount,vo.getAccount()).orderByDesc(User::getId);
        return this.page(page,queryWrapper).convert(UserPageDto::adapt);
    }

    @Override
    public List<User> findByType(Integer i) {
        return lambdaQuery().eq(User::getType, i).list();
    }
}
