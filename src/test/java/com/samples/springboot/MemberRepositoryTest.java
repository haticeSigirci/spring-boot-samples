package com.samples.springboot;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.samples.springboot.domain.Member;
import com.samples.springboot.repository.MemberRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void testFindByName() {
		Member member = new Member("Hatice", "Sığırcı", Date.valueOf(LocalDate.of(2015, 04, 12)), "34755");
		entityManager.persist(member);
		assertEquals("Hatice", member.getFirstName());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void test_add_member() {

		Member member_1 = new Member();
		member_1.setId(1l);
		member_1.setFirstName("hatice");
		member_1.setLastName("sığırcı");
		member_1.setPostalCode("34755");

		LocalDate localDate = LocalDate.of(1991, Month.APRIL, 27);
		member_1.setDateOfBirth(Date.valueOf(localDate)); 
		
		memberRepository.save(member_1);

		List<Member> members = memberRepository.findAll();

		assertEquals(member_1.getFirstName(), members.get(0).getFirstName());

	}

}
