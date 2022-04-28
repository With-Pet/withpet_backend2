package com.withpet.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing 과 관련된 bean 이용가능
@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args)  {

		SpringApplication.run(BackendApplication.class, args);

	}
}
