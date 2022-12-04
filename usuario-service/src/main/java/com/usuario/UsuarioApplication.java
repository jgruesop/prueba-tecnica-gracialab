package com.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UsuarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}
}
