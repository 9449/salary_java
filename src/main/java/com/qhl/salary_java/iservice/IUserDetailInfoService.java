package com.qhl.salary_java.iservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qhl.salary_java.entity.po.UserDetailInfo;
import com.qhl.salary_java.request.salary.UserDetailInfoPageVo;

import java.util.Date;
import java.util.List;

/**
 * @author qhl
 * @date 2023/4/28 15:09
 */

public interface IUserDetailInfoService extends IService<UserDetailInfo> {
    IPage<UserDetailInfo> detailInfos(UserDetailInfoPageVo vo);

    UserDetailInfo getByUidAndDate(Long uid, Date date);

    List<UserDetailInfo> getByDateAndUids(Date date,List<Long> uids);
}
