package com.usuario.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RolregistroDTO {
    private Integer id;
    private String nombre;

    public RolregistroDTO(String nombre) {
        super();
        this.nombre = nombre;
    }
}
