package com.qhl.salary_java.security;


public class UserStatus {

    public static ThreadLocal<LoginUserInfo> userInfo = new ThreadLocal<LoginUserInfo>();

    /**
     * 获取本次访问的用户id
     *
     * @return
     */
    public static LoginUserInfo getUserInfo() {
        return userInfo.get();
    }

    public static void setUserInfo(LoginUserInfo data) {
        userInfo.set(data);
    }

}
