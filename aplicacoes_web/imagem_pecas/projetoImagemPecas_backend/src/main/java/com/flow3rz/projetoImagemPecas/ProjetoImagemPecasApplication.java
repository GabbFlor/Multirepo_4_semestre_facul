package com.flow3rz.projetoImagemPecas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoImagemPecasApplication {

	// bgl util: aparentemente isso aqui é um negocio para testar uma parte do codigo especifico
//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ImageRepository repository) {
//		return args -> {
//			Image image = Image.builder()
//					.extension(ImageExtension.PNG)
//					.name("Imagi top")
//					.tags("teste")
//					.size(1000L)
//					.build();
//			repository.save(image);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoImagemPecasApplication.class, args);
	}

}
