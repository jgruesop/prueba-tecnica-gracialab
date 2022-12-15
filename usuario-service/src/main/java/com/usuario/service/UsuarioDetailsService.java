package com.usuario.service;

import com.usuario.pojo.RespuestaLoginPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    //private final Mensajes  mensajes;
    private final IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RespuestaLoginPojo usuario = usuarioService.findByIdUsuario(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado, username: " + username);
        }
        return new MyUserPrincipal(usuario);
    }
}
