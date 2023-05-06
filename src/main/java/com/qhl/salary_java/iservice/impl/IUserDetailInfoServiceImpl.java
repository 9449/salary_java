package com.qhl.salary_java.iservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.entity.po.UserDetailInfo;
import com.qhl.salary_java.iservice.IUserDetailInfoService;
import com.qhl.salary_java.mapper.UserDetailInfoMapper;
import com.qhl.salary_java.request.salary.UserDetailInfoPageVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author qhl
 * @date 2023/4/28 15:10
 */

@Service
public class IUserDetailInfoServiceImpl extends ServiceImpl<UserDetailInfoMapper, UserDetailInfo> implements IUserDetailInfoService {

    @Override
    public IPage<UserDetailInfo> detailInfos(UserDetailInfoPageVo vo) {
        IPage<UserDetailInfo> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        LambdaQueryWrapper<UserDetailInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Objects.nonNull(vo.getUid()),UserDetailInfo::getUid,vo.getUid())
                .like(Objects.nonNull(vo.getKey()),UserDetailInfo::getAccount,vo.getKey())
                .orderByDesc(UserDetailInfo::getDate);
        return this.page(page,queryWrapper);
    }

    @Override
    public UserDetailInfo getByUidAndDate(Long uid, Date date) {
        return lambdaQuery().eq(UserDetailInfo::getUid,uid).eq(UserDetailInfo::getDate,date).one();
    }

    @Override
    public List<UserDetailInfo> getByDateAndUids(Date date, List<Long> uids) {
        return lambdaQuery().eq(UserDetailInfo::getDate,date).in(UserDetailInfo::getUid,uids).list();
    }
}
