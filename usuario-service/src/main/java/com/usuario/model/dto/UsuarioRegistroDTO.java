package com.usuario.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRegistroDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    public UsuarioRegistroDTO(String nombre, String apellido, String email, String password, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}
