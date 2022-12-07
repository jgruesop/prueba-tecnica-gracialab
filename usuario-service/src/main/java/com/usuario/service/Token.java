package com.usuario.service;

import com.usuario.model.dto.LoginRegistroDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@ConstructorBinding
@ConfigurationProperties
@Getter
public class Token {
    private String token;
    private final static String TOKEN_SECRETO_ACCESO = "unallavesecretamuyextensasegurayconfiableparaelrestauranteshonatale";
    private final static Long TOKEN_ACCESO_VALIDADO_EN_SEGUNDOS = 2L;


    private final LoginRegistroDTO loginRegistroDTO;
    public Token(String token, LoginRegistroDTO loginRegistroDTO) {
        this.token = token;
        this.loginRegistroDTO = loginRegistroDTO;
    }

    public String crearToken(String usuarioId) {

        token = Jwts.builder()
                .setSubject(usuarioId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRETO_ACCESO)
                .compact();
        return token;
    }

    public boolean validarToken(String token, UserDetails userdetails) {
        return userdetails.getUsername().equals(extraerUsernameDelToken(token)) && !haExpiradoElToken(token);
    }

    public String extraerUsernameDelToken(String token) {
        return obtenerObjetosDelToken(token).getSubject();
    }

    public boolean haExpiradoElToken(String token) {
        return obtenerObjetosDelToken(token).getExpiration().before(new java.util.Date());
    }

    public Claims obtenerObjetosDelToken(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRETO_ACCESO).parseClaimsJws(token).getBody();
                //(Claims) Jwts.parserBuilder().requireAudience(TOKEN_SECRETO_ACCESO).build().parse(token);
    }
}
