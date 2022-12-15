package com.usuario.service;

import com.usuario.model.dto.LoginRegistroDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@ConstructorBinding
@ConfigurationProperties
@Getter @Slf4j
public class TokenService {
    private String token;
    private final static String TOKEN_SECRETO_ACCESO = "unallavesecretamuyextensasegurayconfiableparaelrestauranteshonatale=";
    private final static Long TOKEN_ACCESO_VALIDADO_EN_SEGUNDOS = 100 * 60 * 60 * 2L ;


    private final LoginRegistroDTO loginRegistroDTO;
    public TokenService(String token, LoginRegistroDTO loginRegistroDTO) {
        this.token = token;
        this.loginRegistroDTO = loginRegistroDTO;
    }

    public String crearToken(String usuarioId) {

        token = Jwts.builder()
                .setSubject(usuarioId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_ACCESO_VALIDADO_EN_SEGUNDOS))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRETO_ACCESO)
                .compact();
        return token;
    }

    public boolean validarToken(String token, UserDetails userdetails) {
        try {
            Jwts.parser().setSigningKey(TOKEN_SECRETO_ACCESO).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String extraerUsernameDelToken(String token) {
        return obtenerObjetosDelToken(token).getSubject();
    }

    public boolean haExpiradoElToken(String token) {
        return obtenerObjetosDelToken(token).getExpiration().before(new java.util.Date());
    }

    public Claims obtenerObjetosDelToken(String token) {
        //(Claims) Jwts.parserBuilder().requireAudience(TOKEN_SECRETO_ACCESO).build().parse(token);
        return Jwts.parser().setSigningKey(TOKEN_SECRETO_ACCESO).parseClaimsJws(token).getBody();

    }
}
