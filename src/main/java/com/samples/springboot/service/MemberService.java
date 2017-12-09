package com.samples.springboot.service;

import java.util.List;

import com.samples.springboot.domain.Member;

/**
 * Service Interface for managing Member.
 */
public interface MemberService {

	/**
	 * Save a Member.
	 *
	 * @param Member entity to save
	 * @return the persisted entity
	 */
	Member createMember(Member member);

	/**
	 * Get all the Member.
	 * 
	 * @return the list of entities
	 */
	List<Member> findAllMembers();

	/**
	 * Get the "id" Member.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Member findOneById(Long id);

	/**
	 * Delete the "id" Member.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

	/**
	 * Update a Member.
	 *
	 * @param Member  entity to save
	 */
	Member updateMember(Member member);

}
