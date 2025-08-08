package com.acampoverde.ms_account_movement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository")

public class MsAccountMovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccountMovementApplication.class, args);
	}

}
