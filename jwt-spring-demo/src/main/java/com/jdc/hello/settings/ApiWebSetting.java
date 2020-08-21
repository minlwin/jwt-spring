package com.jdc.hello.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.spring.jwt.JwtSettings;

@Configuration
@EnableWebMvc
public class ApiWebSetting implements WebMvcConfigurer {
	
	@Autowired
	private JwtSettings jwtSettings;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("*").allowedMethods("*").allowedHeaders(jwtSettings.tokenName());
	}
}
