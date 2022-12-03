package com.usuario.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUsuarioPojo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;

}
