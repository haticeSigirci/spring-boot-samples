package com.samples.springboot;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samples.springboot.AssesmentApplication;
import com.samples.springboot.domain.Member;
import com.samples.springboot.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AssesmentApplication.class)
@AutoConfigureMockMvc
public class MemberRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	// @InjectMocks
	// MemberRestController memberRestController;

	@MockBean
	private MemberService memberServiceMock;

	private static final String MEMBER = "{" + "\"id\":\"6\"," + "\"firstName\":\"hatice\","
			+ "\"lastName\":\"sığırcı\"," + "\"dateOfBirth\":\"1991-04-27\"," + "\"postalCode\":\"34755\"}";


	@Test
	public void create_member() throws Exception {

		Member member = mock(Member.class);
		member.setFirstName("hatice");
		member.setId(6l);
		member.setDateOfBirth(Date.valueOf(LocalDate.of(1991, Month.APRIL, 27)));
		member.setLastName("sığırcı");
		member.setPostalCode("34755");

		when(memberServiceMock.createMember(member)).thenReturn(member);

		MvcResult result = mockMvc
				.perform(post("/create").contentType(contentType).content(MEMBER)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())

				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	public void delete_member() throws Exception {
		mockMvc.perform(delete("/delete/1")).andExpect(status().isOk());
	}

	@Test
	public void get_all_members() throws Exception {

		LocalDate localDate = LocalDate.of(1991, Month.APRIL, 27);
		List<Member> members = Arrays.asList(new Member("hatice", "sığırcı", Date.valueOf(localDate), "34755"),
				new Member("john", "doe", Date.valueOf(localDate), "12345"),
				new Member("omur", "kumru", Date.valueOf(LocalDate.of(1990, Month.MAY, 07)), "12345"),
				new Member("hatice", "gılbaz", Date.valueOf(LocalDate.of(1989, Month.MARCH, 01)), "12345"),
				new Member("ece", "alkan", Date.valueOf(LocalDate.of(1991, Month.JUNE, 03)), "55555"));

		when(memberServiceMock.findAllMembers()).thenReturn(members);

		mockMvc.perform(get("/members")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void get_member_should_find_member() throws Exception {
		LocalDate localDate = LocalDate.of(1991, Month.APRIL, 27);
		when(memberServiceMock.findOneById(1l))
				.thenReturn(new Member("hatice", "sığırcı", Date.valueOf(localDate), "34755"));

		mockMvc.perform(get("/member/{id}", 1l)).andDo(print()).andExpect(status().isAccepted());

	}

	@Test
	public void update_member() throws Exception {
		mockMvc.perform(put("/update/{id}", 1l).contentType(MediaType.APPLICATION_JSON).content(MEMBER)).andDo(print())
				.andExpect(status().isAccepted());
	}

}
