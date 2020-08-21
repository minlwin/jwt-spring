package com.jdc.hello.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.hello.api.dto.LoginDto;
import com.jdc.hello.api.dto.LoginResultDto;
import com.jdc.hello.model.entity.Member;
import com.jdc.hello.model.service.SecurityService;

@RestController
@RequestMapping("security")
public class SecurityApi {

	@Autowired
	private SecurityService security;
	
	@PostMapping("login")
	public LoginResultDto login(@RequestBody LoginDto dto) {
		return security.login(dto);
	}

	@PostMapping("signup")
	public LoginResultDto signup(@RequestBody Member member) {
		return security.signUp(member);
	}

}
