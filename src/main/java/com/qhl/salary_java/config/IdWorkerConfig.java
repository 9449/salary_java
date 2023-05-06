package com.qhl.salary_java.config;

import com.qhl.salary_java.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdWorkerConfig {

	@Bean
	public SnowflakeIdWorker getIdWorker(){
		return new SnowflakeIdWorker(1, 1);
	}
}
