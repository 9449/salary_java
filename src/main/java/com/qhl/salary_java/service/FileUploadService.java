package com.qhl.salary_java.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mysql.jdbc.log.Log;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.entity.po.UserDetailInfo;
import com.qhl.salary_java.entity.po.UserSalaryDetail;
import com.qhl.salary_java.iservice.IUserDetailInfoService;
import com.qhl.salary_java.iservice.IUserSalaryDetailService;
import com.qhl.salary_java.iservice.IUserService;
import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.utils.DateFormatUtil;
import com.qhl.salary_java.utils.excel.dto.ExcelDto;
import com.qhl.salary_java.utils.excel.listener.ImportExcelListener;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qhl
 * @date 2023-5-5 16:36
 */
@Service
@Slf4j
public class FileUploadService {
    private static int MAX_MONTH = 12;

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserDetailInfoService userDetailInfoService;
    @Autowired
    private IUserSalaryDetailService userSalaryDetailService;

    @Transactional(rollbackFor = Exception.class)
    public WebApiResponse upload(MultipartFile file) {
        if (!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")) {
            return WebApiResponse.error("请选择正确的文件");
        }
        try {
            InputStream inputStream = file.getInputStream();
            List<ExcelDto> list = new ArrayList<>();
            EasyExcel.read(inputStream, ExcelDto.class, new AnalysisEventListener() {
                @Override
                public void invoke(Object data, AnalysisContext context) {
                    list.add((ExcelDto) data);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {

                }
            }).headRowNumber(0).sheet().doRead();
            // 获取到的list
            if (list.size() <= 3) {
                return WebApiResponse.error("选择正确的文件");
            }
            Integer month = Integer.valueOf(list.get(0).getColumn_1().split("月份基本工资")[0]);
            if (month > MAX_MONTH) {
                return WebApiResponse.error("输入正确的月份");
            }
            List<String> accounts = new ArrayList<>();
            for (int i = 3; i < list.size(); i++) {
                accounts.add(list.get(i).getColumn_2());
            }
            List<User> userList = userService.findByAccounts(accounts);
            if (CollectionUtils.isEmpty(userList)) {
                return WebApiResponse.success("填写正确的账号");
            }
            List<Long> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
            String dateStr = DateFormatUtil.monthToDateStr(month);
            Date date = DateFormatUtil.StringToDate(dateStr,"yyyy-MM");
            List<UserSalaryDetail> userSalaryDetails = userSalaryDetailService.findByUidsAndDate(userIds, dateStr);
            // 获取已有的简阅数据
            List<UserDetailInfo> userDetailInfos = userDetailInfoService.getByDateAndUids(date, userIds);
            // 要添加的每月简阅
            List<UserDetailInfo> addUserDetailInfos = new ArrayList<>();
            // 要修改的每月简阅
            List<UserDetailInfo> updateUserDetailInfos = new ArrayList<>();
            // 要添加的详情
            List<UserSalaryDetail> userSalaryDetailList =  new ArrayList<>();
            for (int i = 3; i < list.size(); i++) {
                ExcelDto excelDto = list.get(i);
                // 判断是否存在姓名  姓名是否存在  是否输入本月实发和是否为数字
                if (excelDto.getColumn_2().isEmpty() || validUser(userList,excelDto.getColumn_2()) || validRealWages(excelDto.getColumn_20())) {
                    continue;
                }
                User user = userList.stream().filter(u -> u.getAccount().equals(excelDto.getColumn_2())).findFirst().orElse(null);
                if (user == null) {
                    continue;
                }
                // 获取所有的属性创建详情
                Field[] fields = excelDto.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Long templateId = -1L;
                    switch (field.getName()) {
                        case "column_2":
                            templateId = 1l;
                            break;
                        case "column_3":
                            templateId = 15l;
                            break;
                        case "column_4":
                            templateId = 16l;
                            break;
                        case "column_5":
                            templateId = 17l;
                            break;
                        case "column_6":
                            templateId = 18l;
                            break;
                        case "column_7":
                            templateId = 19l;
                            break;
                        case "column_8":
                            templateId = 20l;
                            break;
                        case "column_9":
                            templateId = 3l;
                            break;
                        case "column_10":
                            templateId = 4l;
                            break;
                        case "column_11":
                            templateId = 5l;
                            break;
                        case "column_12":
                            templateId = 6l;
                            break;
                        case "column_13":
                            templateId = 7l;
                            break;
                        case "column_14":
                            templateId = 8l;
                            break;
                        case "column_15":
                            templateId = 9l;
                            break;
                        case "column_16":
                            templateId = 10l;
                            break;
                        case "column_17":
                            templateId = 11l;
                            break;
                        case "column_18":
                            templateId = 12l;
                            break;
                        case "column_19":
                            templateId = 13l;
                            break;
                        case "column_20":
                            templateId = 14l;
                            break;



                    }
                    if (templateId.intValue() < 0) {
                        continue;
                    }
                    UserSalaryDetail userSalaryDetail = new UserSalaryDetail();
                    userSalaryDetail.setContent(String.valueOf(field.get(excelDto)));
                    userSalaryDetail.setDate(dateStr);
                    userSalaryDetail.setTemplateId(templateId);
                    userSalaryDetail.setUid(user.getId());
                    userSalaryDetailList.add(userSalaryDetail);
                }
                UserDetailInfo saveUserDetailInfo = new UserDetailInfo();
                // 需要添加的
                for (UserDetailInfo userDetailInfo : userDetailInfos) {
                    if (DateFormatUtil.dateToString(userDetailInfo.getDate(),"yyyy-MM").equals(dateStr) && userDetailInfo.getUid().equals(user.getId())) {
                        BeanUtils.copyProperties(userDetailInfo,saveUserDetailInfo);
                        saveUserDetailInfo.setRealWages(new BigDecimal(excelDto.getColumn_20()));
                        updateUserDetailInfos.add(saveUserDetailInfo);
                        break;
                    }
                }
                if (saveUserDetailInfo.getId() == null) {
                    saveUserDetailInfo.setDate(date);
                    saveUserDetailInfo.setAccount(user.getAccount());
                    saveUserDetailInfo.setUid(user.getId());
                    saveUserDetailInfo.setRealWages(new BigDecimal(excelDto.getColumn_20()));
                    addUserDetailInfos.add(saveUserDetailInfo);
                }
            }
            // 删除已有的数据
//            userSalaryDetailService.removeBatchByIds(userSalaryDetails);
            userSalaryDetailService.updateBatchById(userSalaryDetails.stream().map(i -> {
                i.setIsDelete(System.currentTimeMillis());
                return i;
            }).collect(Collectors.toList()));
            // 添加数据
            userSalaryDetailService.saveBatch(userSalaryDetailList);
            // 修改数据
            userDetailInfoService.updateBatchById(updateUserDetailInfos);
            // 添加数据
            userDetailInfoService.saveBatch(addUserDetailInfos);
        } catch (Exception e) {
            return WebApiResponse.error(e.getMessage());
        }
        return WebApiResponse.success("导入成功");
    }

    // 判断account是否存在人员中
    private boolean validUser(List<User> users,String account) {
        List<User> collect = users.stream().filter(u -> u.getAccount().equals(account)).collect(Collectors.toList());
        return CollectionUtils.isEmpty(collect);
    }
    // 判断是不是数字
    private boolean validRealWages(String realWages) {
        return !realWages.matches("[0-9]+.?[0-9]*");
    }


}
