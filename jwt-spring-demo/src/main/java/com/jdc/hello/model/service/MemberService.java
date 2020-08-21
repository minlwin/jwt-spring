package com.jdc.hello.model.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.hello.model.entity.Member;
import com.jdc.hello.model.repo.MemberRepo;

@Service
public class MemberService {

	@Autowired
	private MemberRepo repo;
	
	public Member findByEmail(String userName) {
		return repo.findById(userName).orElseThrow(() -> new EntityNotFoundException());
	}
}
