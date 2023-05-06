package com.qhl.salary_java.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhl.salary_java.entity.po.SalaryTemplate;
import com.qhl.salary_java.entity.po.SalaryTemplateShield;
import com.qhl.salary_java.entity.po.UserDetailInfo;
import com.qhl.salary_java.entity.po.UserSalaryDetail;
import com.qhl.salary_java.iservice.ISalaryTemplateService;
import com.qhl.salary_java.iservice.ISalaryTemplateShieldService;
import com.qhl.salary_java.iservice.IUserDetailInfoService;
import com.qhl.salary_java.iservice.IUserSalaryDetailService;
import com.qhl.salary_java.request.salary.UserDetailInfoPageVo;
import com.qhl.salary_java.request.salary.UserSalaryDetailVo;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.response.dto.SalaryDetailMonthDto;
import com.qhl.salary_java.utils.DateFormatUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author qhl
 * @date 2023/4/24 17:24
 */
@Service
public class UserSalaryService {
    @Autowired
    private IUserSalaryDetailService userSalaryDetailService;
    @Autowired
    private IUserDetailInfoService userDetailInfoService;
    @Autowired
    private ISalaryTemplateService iSalaryTemplateService;
    @Autowired
    private ISalaryTemplateShieldService templateShieldService;

    @Transactional(rollbackFor = Exception.class)
    public WebApiResponse add(UserSalaryDetailVo vo) {
        String date = vo.getDate();
        Long uid = vo.getUid();
        Map jsonObject = JSONObject.parseObject(vo.getUserSalaryDetail());
        List<UserSalaryDetail> addDetails = new ArrayList<>();
        for (Object o : jsonObject.keySet()) {
            UserSalaryDetail addDetail = new UserSalaryDetail();
            addDetail.setUid(uid);
            addDetail.setDate(date);
            addDetail.setTemplateId(Long.valueOf(o.toString()));
            addDetail.setContent(jsonObject.get(o).toString());
            addDetails.add(addDetail);
        }
        UserSalaryDetail userSalaryDetail = addDetails.stream().filter(item -> item.getTemplateId().equals(14L)).findFirst().orElse(null);
        if (userSalaryDetail == null) {
            return WebApiResponse.error("请输入实发工资");
        }
        UserDetailInfo userDetailInfo = userDetailInfoService.getByUidAndDate(vo.getUid(), DateFormatUtil.StringToDate(userSalaryDetail.getDate(), "yyyy-MM"));
        if (userDetailInfo == null) {
            UserDetailInfo addUserDetailInfo = new UserDetailInfo();
            addUserDetailInfo.setDate(DateFormatUtil.StringToDate(userSalaryDetail.getDate(),"yyyy-MM"));
            addUserDetailInfo.setUid(vo.getUid());
            addUserDetailInfo.setRealWages(new BigDecimal(userSalaryDetail.getContent()));
            addUserDetailInfo.setAccount(vo.getAccount());
            userDetailInfoService.save(addUserDetailInfo);
        } else {
            userDetailInfo.setRealWages(new BigDecimal(userSalaryDetail.getContent()));
            userDetailInfoService.updateById(userDetailInfo);
        }
        List<UserSalaryDetail> details = userSalaryDetailService.findByUidAndDate(uid, date);
        // 删除已有的
        if (!details.isEmpty()) {
            userSalaryDetailService.update(
                    new LambdaUpdateWrapper<UserSalaryDetail>()
                            .eq(UserSalaryDetail::getUid,uid)
                            .eq(UserSalaryDetail::getDate,date)
                            .set(UserSalaryDetail::getIsDelete,System.currentTimeMillis())
            );
        }
        boolean b = false;
        // 插入新数据
        if (!addDetails.isEmpty()) {
             b = userSalaryDetailService.saveBatch(addDetails);
        }
        if (b) {
            return WebApiResponse.success("操作成功");
        }
        return WebApiResponse.error("操作失败");
    }

    public WebApiResponse<IPage<UserDetailInfo>> detailInfos(UserDetailInfoPageVo vo) {
        return WebApiResponse.success(userDetailInfoService.detailInfos(vo));
    }

    public WebApiResponse detailMonth(Long id) {
        UserDetailInfo userDetailInfo = userDetailInfoService.getById(id);
        if (userDetailInfo == null) {
            return WebApiResponse.error("INFO NOT FOUND");
        }
        List<SalaryDetailMonthDto> dtos = new ArrayList<>();
        List<UserSalaryDetail> details = userSalaryDetailService.findByUidAndDate(userDetailInfo.getUid(), DateFormatUtil.dateToString(userDetailInfo.getDate(),"yyyy-MM"));
        List<SalaryTemplate> salaryTemplates = iSalaryTemplateService.findByPid(0L);
        List<Long> templateIds = templateShieldService.list().stream().map(SalaryTemplateShield::getTemplateId).collect(Collectors.toList());
        for (SalaryTemplate salaryTemplate : salaryTemplates) {
            if (!templateIds.contains(salaryTemplate.getId())) {
                SalaryDetailMonthDto dto = new SalaryDetailMonthDto();
                BeanUtils.copyProperties(salaryTemplate,dto);
                String fContent = getContent(details, salaryTemplate.getId());
                dto.setContent(fContent);
                if (salaryTemplate.getType() == 3) {
                    List<SalaryTemplate> sons = iSalaryTemplateService.findByPid(salaryTemplate.getId());
                    List<SalaryDetailMonthDto> sonDtos = new ArrayList<>();
                    for (SalaryTemplate son : sons) {
                        if (!templateIds.contains(son.getId())) {
                            SalaryDetailMonthDto sonDto = new SalaryDetailMonthDto();
                            BeanUtils.copyProperties(son,sonDto);
                            String content = getContent(details, son.getId());
                            sonDto.setContent(content);
                            sonDtos.add(sonDto);
                        }
                    }
                    dto.setList(sonDtos);
                }
                dtos.add(dto);
            }
        }
        return WebApiResponse.success(dtos);
    }


    private String getContent(List<UserSalaryDetail> details,Long id) {
        String result = "";
        for (UserSalaryDetail detail : details) {
            if (detail.getTemplateId().equals(id)) {
                result = detail.getContent();
            }
        }
        return result;
    }
}
