package com.qhl.salary_java.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 2019-05-05
 *
 * @author noxus
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class DataBaseConfig {

    @Bean(name = "pressure1DataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.druid.*")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name="pressure1Template")
    public JdbcTemplate pressure1Template (
            @Qualifier("pressure1DataSource")  DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }

}
