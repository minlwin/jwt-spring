package com.jdc.hello.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.hello.model.entity.Member;

@Service
public class ApiUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberService members;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = members.findByEmail(username);
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.authorities(member.getRole().name())
				.accountExpired(false)
				.accountLocked(false)
				.build();
	}

}
