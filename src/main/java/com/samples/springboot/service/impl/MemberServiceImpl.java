package com.samples.springboot.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samples.springboot.domain.Member;
import com.samples.springboot.repository.MemberRepository;
import com.samples.springboot.service.MemberService;

/**
 * Service Implementation for managing Member.
 */
@Service 
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberRepository memberRepository; 
	
	/**
    *  Get all the Members.
    *  
    *  @return the list of entities
    */
	@Override
	@Transactional(readOnly = true)
	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

	/**
     *  Get one Member by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
	@Override
	@Transactional(readOnly = true)
	public Member findOneById(Long id) {
		return memberRepository.findOne(id);
	}

	/**
     *  Delete the Member by id.
     *
     *  @param id the id of the entity
     */
	@Override
	public void delete(Long id) {
		memberRepository.delete(id);	
	}

	 /**
     * Save a Member.
     *
     * @param Member the entity to save
     * @return the persisted entity
     */
	@Override
	public Member createMember(Member member) {
		Member result =  memberRepository.save(member);
		return result;
	}

	 /**
     * Update a Member.
     *
     * @param Member the entity to save
     */
	@Override
	public Member updateMember(Member member) {
		return memberRepository.save(member);
	}

}
