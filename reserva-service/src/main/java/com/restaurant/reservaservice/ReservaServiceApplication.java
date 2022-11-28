package com.restaurant.reservaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ReservaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaServiceApplication.class, args);
	}

	//Configuración de los CORS para las peticiones desde el frontend
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
}
