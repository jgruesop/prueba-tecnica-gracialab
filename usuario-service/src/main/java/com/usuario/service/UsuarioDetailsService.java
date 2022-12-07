package com.usuario.service;

import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaLoginPojo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final Mensajes  mensajes;
    private final IUsuarioService usuarioService;
    private final RespuestaLoginPojo respuestaLoginPojo;

    public UsuarioDetailsService(Mensajes mensajes, IUsuarioService usuarioService, RespuestaLoginPojo respuestaLoginPojo) {
        this.mensajes = mensajes;
        this.usuarioService = usuarioService;
        this.respuestaLoginPojo = respuestaLoginPojo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        var usuario = usuarioService.buscarUsuario(username);
        System.out.println("usuario.getId() = " + usuario.getId());
        System.out.println("usuario = " + usuario);
        if(usuario == null) {
            mensajes.errorTokenBearer();
        }
        return new User(respuestaLoginPojo.getEmail(), respuestaLoginPojo.getPassword(), Collections.emptyList());
    }


}
