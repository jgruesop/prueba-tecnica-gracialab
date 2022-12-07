package com.usuario.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Collection;

@ConstructorBinding
@ConfigurationProperties
@Getter   @Setter
@AllArgsConstructor
public class RespuestaLoginPojo {
    private String id;
    private String email;
    private String password;
    private Collection rol;

}
