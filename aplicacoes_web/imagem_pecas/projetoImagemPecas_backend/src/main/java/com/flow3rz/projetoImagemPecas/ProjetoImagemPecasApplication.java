package com.flow3rz.projetoImagemPecas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoImagemPecasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoImagemPecasApplication.class, args);
	}

}
