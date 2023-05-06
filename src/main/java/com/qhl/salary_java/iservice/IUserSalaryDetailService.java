package com.qhl.salary_java.iservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qhl.salary_java.entity.po.UserSalaryDetail;

import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 11:46
 */

public interface IUserSalaryDetailService extends IService<UserSalaryDetail> {
    List<UserSalaryDetail> findByUidAndDate(Long uid, String date);

    List<UserSalaryDetail> findByUidsAndDate(List<Long> uids, String date);

}
