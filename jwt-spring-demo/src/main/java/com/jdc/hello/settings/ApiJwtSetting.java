package com.jdc.hello.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.hello.model.BaseRepositoryImpl;
import com.jdc.spring.jwt.JwtConfiguration;
import com.jdc.spring.jwt.JwtSettings;

@Configuration
@EnableJpaRepositories(basePackages = "com.jdc.hello.model.repo", repositoryBaseClass = BaseRepositoryImpl.class)
public class ApiJwtSetting implements JwtConfiguration {
	
	@Value("${app.jwt.token.issuer}")
	private String issuer;
	@Value("${app.jwt.token.name}")
	private String tokenName;
	@Value("${app.jwt.token.lifetime}")
	private int lifetime;
	
	@Override
	public JwtSettings settings() {
		return new JwtSettings() {
			
			@Override
			public String tokenName() {
				return tokenName;
			}
			
			@Override
			public int lifeTimeInMinutes() {
				return lifetime;
			}
			
			@Override
			public String issuer() {
				return issuer;
			}
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
