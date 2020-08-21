package com.jdc.hello.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	private String email;
	private String name;
	private String phone;
	private String password;
	private Role role;
	
	public enum Role {
		Admin, Member
	}
}
