package com.jdc.hello.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.hello.api.dto.LoginDto;
import com.jdc.hello.api.dto.LoginResultDto;
import com.jdc.hello.model.entity.Member;

@RestController
@RequestMapping("members")
public class MemberApi {

	@PostMapping("login")
	public LoginResultDto login(@RequestBody LoginDto dto) {
		return null;
	}

	@PostMapping("signup")
	public LoginResultDto signup(@RequestBody Member dto) {
		return null;
	}
}
