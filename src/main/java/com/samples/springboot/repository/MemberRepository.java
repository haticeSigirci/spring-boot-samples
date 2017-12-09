package com.samples.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samples.springboot.domain.Member;

/**
 * Spring Data JPA repository for the Member entity.
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
