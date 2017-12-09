package com.samples.springboot.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samples.springboot.domain.Member;
import com.samples.springboot.service.MemberService;
import com.samples.springboot.util.HeaderUtil;

/**
 * REST controller for managing Member.
 */
@RestController
public class MemberRestController {

	@Autowired 
	MemberService memberService;

	private static final String ENTITY_NAME = "Member";

	/**
	 * POST /member : Create a new Member.
	 *
	 * @param Member to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new Member, or with status 400 (Bad Request) if the Member has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/create")
	public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) throws URISyntaxException { 
//		if (member.getId() != null) {
//			return ResponseEntity.badRequest().headers(
//					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Member cannot already have an ID"))
//					.body(null);
//		}
		Member result = memberService.createMember(member);
		return ResponseEntity.created(new URI("/create" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * GET /members : get all the Members.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of Member in body
	 */
	
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllmembers() {
		
		List<Member> list = memberService.findAllMembers();
		return new ResponseEntity<>(list, null, HttpStatus.OK);
	}

	
	
	
	/**
     * GET  /member/:id : get the "id" Member.
     *
     * @param id the id of the Member to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Member, or with status 404 (Not Found)
     */
	@GetMapping("/member/{id}")
	public ResponseEntity<Member> getMember(@PathVariable Long id) {
		Member member = memberService.findOneById(id);
		return ResponseEntity.accepted().body(member);
	}

	/**
     * PUT  /update : Updates an existing Member.
     *
     * @param Member to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Member,
     * or with status 400 (Bad Request) if the Member is not valid,
     * or with status 500 (Internal Server Error) if the Member couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
	@PutMapping("/update/{id}")
	public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member) throws URISyntaxException { 
//		if (member.getId() == null) {
//            return createMember(member);
//        }
		Member result = memberService.updateMember(member);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, member.getId().toString())).body(result);
	}

	/**
	 * DELETE /delete/:id : delete the "id" Member.
	 *
	 * @param id the id of the Member to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
		memberService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
