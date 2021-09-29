package com.jdc.spring.jwt.services;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class JwtTokenAdvice implements ResponseBodyAdvice<Object>{

	private JwtTokenProvider provider;
	
	public JwtTokenAdvice(@NonNull JwtTokenProvider provider) {
		super();
		this.provider = provider;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(validUser(authentication)) {
			var jwtToken = provider.generate(authentication);
			var resp = (ServletServerHttpResponse) response;
			resp.getServletResponse().addHeader(provider.tokenName(), jwtToken);
		}
		
		return body;
	}

	private boolean validUser(Authentication auth) {
		return null != auth && !(auth instanceof AnonymousAuthenticationToken);
	}

}
