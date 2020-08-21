package com.jdc.hello.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.hello.model.BaseRepositoryImpl;
import com.jdc.spring.jwt.JwtConfiguration;

@Configuration
@EnableJpaRepositories(basePackages = "com.jdc.hello.model.repo", repositoryBaseClass = BaseRepositoryImpl.class)
public class ApiJwtSetting implements JwtConfiguration {
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
