package com.usuario.security.filter;

import com.usuario.model.util.Mensajes;
import com.usuario.service.Token;
import com.usuario.service.UsuarioDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component @Slf4j
public class JWTFilterRequest extends OncePerRequestFilter {


    private final UsuarioDetailsService usuarioDetailsService;
    private final Token token;
    private final Mensajes mensajes;

    public JWTFilterRequest(UsuarioDetailsService usuarioDetailsService, Token token, Mensajes mensajes) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.token = token;
        this.mensajes = mensajes;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        try {
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                String jwt = authorizationHeader.substring(7);
                String usuario = token.extraerUsernameDelToken(jwt);

                if(usuario != null && SecurityContextHolder.getContext().getAuthentication() != null)
                    mensajes.errorTokenBearer();

                UserDetails userdetails = usuarioDetailsService.loadUserByUsername(usuario);

                if(token.validarToken(jwt, userdetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userdetails.getUsername(),
                            userdetails.getPassword(),
                            userdetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }catch(Exception e) {
            log.info("Se produjo un error en la clase JTWFilterRequest: " + e.getMessage());
        }
    }
}
