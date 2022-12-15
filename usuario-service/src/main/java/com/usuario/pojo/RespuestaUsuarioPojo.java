package com.usuario.pojo;

import antlr.collections.List;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties
@Getter   @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaUsuarioPojo {
    private Integer id_usuario;
    private String email;
    private String password;
    private List rol_nombre;
}
