package com.usuario.service;

import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaLoginPojo;
import com.usuario.pojo.RespuestaUsuarioPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service @RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final Mensajes  mensajes;
    private final IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioService.findByIdUsuario(username);
        System.out.println("poso prueba UserDetails - usuario.getId() = " + usuario.getId());
        System.out.println("usuario = " + usuario);
        if(usuario == null) {
            mensajes.errorTokenBearer();
        }
        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getRol());
    }
}
