package com.jdc.hello.api.dto;

import com.jdc.hello.model.entity.Member;

import lombok.Data;

@Data
public class LoginResultDto {

	private Member member;
	private boolean success;
	private String message;
}
