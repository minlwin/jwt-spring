package com.jdc.hello.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.spring.jwt.JwtConfiguration;

@Configuration
@EnableJpaRepositories(basePackages = "com.jdc.hello.model.repo")
public class ApiJwtSetting implements JwtConfiguration {

}
