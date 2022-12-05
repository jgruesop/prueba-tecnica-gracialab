package com.usuario.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Getter
public class Token {
    private final String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token of(Integer usuarioId, Long validacionEnMinutos, String llaveSecreta) {
        var fechaExpedicion = Instant.now();
        return new Token (Jwts.builder()
                                        .claim("usuario_id", usuarioId)
                                        .setIssuedAt(Date.from(fechaExpedicion))
                                        .setExpiration(Date.from(fechaExpedicion.plus(validacionEnMinutos, ChronoUnit.MINUTES)))
                                        .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(llaveSecreta.getBytes(StandardCharsets.UTF_8)))
                                        .compact());
    }
}
