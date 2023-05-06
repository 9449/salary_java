package com.qhl.salary_java.config;

import com.qhl.salary_java.interceptor.AccessTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 2019-05-06
 *
 * @author noxus
 */
@Configuration
public class ApiConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AccessTokenInterceptor accessTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor)
                .addPathPatterns("/api/**/*")
                .excludePathPatterns("/file/upload")
                .excludePathPatterns("/api/user/login");
    }
}
