package com.jdc.spring.jwt;

import org.springframework.context.annotation.Bean;

import com.jdc.spring.jwt.services.JwtTokenAdvice;
import com.jdc.spring.jwt.services.JwtTokenFilter;
import com.jdc.spring.jwt.services.JwtTokenProvider;

public interface JwtConfiguration {

	@Bean
	default JwtTokenAdvice tokenAdvice() {
		return new JwtTokenAdvice(tokenProvider());
	}
	
	@Bean
	default JwtTokenFilter tokenFilter() {
		return new JwtTokenFilter(tokenProvider());
	}
	
	@Bean
	default JwtTokenProvider tokenProvider() {
		return new JwtTokenProvider(settings());
	}
	
	@Bean
	default JwtSettings settings() {
		return new JwtSettings() {
			
			@Override
			public String tokenName() {
				return "JDC-JWT-TOKEN";
			}
			
			@Override
			public int lifeTimeInMinutes() {
				return 1;
			}
			
			@Override
			public String issuer() {
				return "JDC-JWT-LIB";
			}
		};
	}
}
