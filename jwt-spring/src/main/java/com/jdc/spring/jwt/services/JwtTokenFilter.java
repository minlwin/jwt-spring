package com.jdc.spring.jwt.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	private JwtTokenProvider provider;
	
	public JwtTokenFilter(JwtTokenProvider provider) {
		super();
		this.provider = provider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			Authentication auth = provider.parse(request);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			System.err.println("Invalid Token : " + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
}
