package com.jdc.hello.model.service;

import java.util.HashMap;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.hello.model.ApiBusinessException;
import com.jdc.hello.model.entity.Member;
import com.jdc.hello.model.repo.MemberRepo;

@Service
public class MemberService {

	@Autowired
	private MemberRepo repo;
	
	@Value("${app.query.member.count.email}")
	private String memberCountByEmailQuery;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Member findByEmail(String userName) {
		return repo.findById(userName).orElseThrow(() -> new EntityNotFoundException());
	}

	@SuppressWarnings("serial")
	public void create(Member member) {
		
		// Check Member Email is already used
		Long count = repo.count(memberCountByEmailQuery, new HashMap<String, Object>(){
			{ put("email", member.getEmail());}
		});
		
		if(count > 0) {
			throw new ApiBusinessException("Your email address is already used by other member.");
		}
		
		// Encrypt Password
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		repo.save(member);
	}
}
