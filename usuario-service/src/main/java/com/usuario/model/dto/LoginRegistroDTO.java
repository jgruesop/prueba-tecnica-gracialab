package com.usuario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter
@NoArgsConstructor @AllArgsConstructor
public class LoginRegistroDTO {
    private String email;
    private String password;
}
