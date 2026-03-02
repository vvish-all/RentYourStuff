package com.rentyourstuff.appuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppUserServiceApplication.class, args);
	}

}
