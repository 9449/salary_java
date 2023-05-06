package com.qhl.salary_java.interceptor;


import com.qhl.salary_java.constants.ApiConstant;
import com.qhl.salary_java.iservice.IUserService;
import com.qhl.salary_java.iservice.impl.IUserServiceImpl;
import com.qhl.salary_java.entity.po.User;
import com.qhl.salary_java.security.LoginUserInfo;
import com.qhl.salary_java.security.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2019-05-06
 *
 * @author noxus
 */
@Component
@Slf4j
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = checkToken(httpServletRequest);
        if (user == null) {
            log.warn("token校验失败,需重新登录");
            httpServletResponse.setStatus(600);
            return false;
        }

        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setToken(user.getToken());
        loginUserInfo.setUid(user.getId());
        UserStatus.setUserInfo(loginUserInfo);
        return super.preHandle(httpServletRequest, httpServletResponse, o);
    }

    private User checkToken(HttpServletRequest request) {
        String token = getUserToken(request);
        if (StringUtils.isNotEmpty(token)) {
            User user = userService.findByToken(token);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    /**
     * token放置在header里面，key为 Authorization
     * 如果header里找不到，就去cookies里找
     *
     * @param request
     * @return
     */
    private String getUserToken(HttpServletRequest request) {
        String token = request.getHeader(ApiConstant.USER_TOKEN);
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            token = getTokenFromCookie(cookies);
        }
        return token;
    }

    private String getTokenFromCookie(Cookie[] cookies) {
        String token = null;
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (ApiConstant.USER_TOKEN.equalsIgnoreCase(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
