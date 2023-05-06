package com.qhl.salary_java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.qhl.salary_java.mapper")
public class SalaryJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryJavaApplication.class, args);
    }

}
