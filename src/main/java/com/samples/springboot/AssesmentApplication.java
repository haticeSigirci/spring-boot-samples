package com.samples.springboot;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.samples.springboot.domain.Member;
import com.samples.springboot.repository.MemberRepository;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class AssesmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssesmentApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MemberRepository mr) {
		return args -> {
			LocalDate localDate = LocalDate.of(1991, Month.APRIL, 27);
			List<Member> members = Arrays.asList(new Member("hatice", "xxxxx", Date.valueOf(localDate), "12345"),
					new Member("john", "doe", Date.valueOf(localDate), "12345"),
					new Member("john", "doe", Date.valueOf(LocalDate.of(1990, Month.MAY, 07)), "12345"),
					new Member("john", "doe", Date.valueOf(LocalDate.of(1989, Month.MARCH, 01)), "33333"),
					new Member("john", "doe", Date.valueOf(LocalDate.of(1991, Month.JUNE, 06)), "55555"));

			members.stream().forEach(member -> mr.save(member));

			mr.findAll().forEach(System.out::println);
		};
	}
}
