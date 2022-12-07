package com.usuario;

import com.usuario.pojo.RespuestaLoginPojo;
import com.usuario.pojo.RespuestaUsuarioPojo;
import com.usuario.service.Token;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication  @EnableJpaRepositories
@EnableConfigurationProperties({RespuestaLoginPojo.class, RespuestaUsuarioPojo.class, Token.class})
public class UsuarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}
}
