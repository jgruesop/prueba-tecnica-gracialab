package com.usuario.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Collection;

@ConstructorBinding
@ConfigurationProperties
@Getter   @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaUsuarioPojo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Collection rol;
}
