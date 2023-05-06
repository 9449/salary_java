package com.qhl.salary_java.iservice.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhl.salary_java.entity.po.UserSalaryDetail;
import com.qhl.salary_java.iservice.IUserSalaryDetailService;
import com.qhl.salary_java.mapper.UserSalaryDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 11:47
 */
@Service
public class IUserSalaryDetailServiceImpl extends ServiceImpl<UserSalaryDetailMapper, UserSalaryDetail> implements IUserSalaryDetailService {

    @Override
    public List<UserSalaryDetail> findByUidAndDate(Long uid, String date) {
        return lambdaQuery().eq(UserSalaryDetail::getUid,uid).eq(UserSalaryDetail::getDate,date).list();
    }

    @Override
    public List<UserSalaryDetail> findByUidsAndDate(List<Long> uids, String date) {
        return lambdaQuery().in(UserSalaryDetail::getUid,uids).eq(UserSalaryDetail::getDate,date).list();
    }

}
