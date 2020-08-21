package com.jdc.hello.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdc.hello.model.entity.Member;
import com.jdc.hello.model.entity.Member.Role;
import com.jdc.hello.model.service.MemberService;

@Configuration
public class AdminUserInitializer {

	@Autowired
	private MemberService members;
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			if(members.thereIsNoUser()) {
				
				Member member = new Member();
				member.setEmail("admin");
				member.setPassword("admin");
				member.setName("Admin User");
				member.setRole(Role.Admin);
				
				members.create(member);
			}
		};
	}
}
