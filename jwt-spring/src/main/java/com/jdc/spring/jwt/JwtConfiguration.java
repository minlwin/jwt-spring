package com.jdc.spring.jwt;

import org.springframework.context.annotation.Bean;

import com.jdc.spring.jwt.services.JwtTokenAdvice;
import com.jdc.spring.jwt.services.JwtTokenFilter;
import com.jdc.spring.jwt.services.JwtTokenProvider;

/**
 * <h3>Base Configuration </h3>
 * 
 * <p>
 * If you want to use JWT-Spring Library, You have to implement JwtConfiguration Interface.
 * This interface contains default Beans for JWT Token Base Authentication.
 * </p>
 * 
 * @author minlwin
 * @since 2020/08/21
 *
 */
public interface JwtConfiguration {

	/**
	 * tokenAdvice 
	 * 
	 * @return
	 */
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
