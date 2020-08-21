package com.jdc.hello.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.hello.model.BaseRepositoryImpl;
import com.jdc.spring.jwt.JwtConfiguration;

@Configuration
@EnableJpaRepositories(basePackages = "com.jdc.hello.model.repo", repositoryBaseClass = BaseRepositoryImpl.class)
public class ApiJwtSetting implements JwtConfiguration {

}
