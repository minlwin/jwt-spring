package com.jdc.hello.model.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.hello.api.dto.LoginDto;
import com.jdc.hello.api.dto.LoginResultDto;
import com.jdc.hello.model.entity.Member;

@Service
public class SecurityService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MemberService members;

	public LoginResultDto login(LoginDto dto) {
		return login(dto.getLoginId(), dto.getPassword());
	}

	@Transactional
	public LoginResultDto signUp(Member member) {
		members.create(member);
		return login(member.getEmail(), member.getPassword());
	}

	private LoginResultDto login(String loginId, String password) {
		
		LoginResultDto result = new LoginResultDto();
		
		try {
			// authentication by login id and password
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginId, password);
			Authentication authentication = authManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			// Populate Login Result
			result.setMember(members.findByEmail(loginId));
			result.setSuccess(true);
			
		} catch (InternalAuthenticationServiceException | EntityNotFoundException e) {
			result.setMessage("Please Check Your Login Id.");
		} catch (BadCredentialsException e) {
			result.setMessage("Please Check Your Password.");
		}
		
		return result;
	}

}
