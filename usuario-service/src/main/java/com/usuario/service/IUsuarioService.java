package com.usuario.service;

import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.pojo.RespuestaUsuarioPojo;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUsuarioService {

    ResponseEntity listarUsuarios();

    RespuestaUsuarioPojo buscarUsuario(String id);

    Usuario registrarUsuario(Usuario usuario);

    String buscarEmail(String email);

    ResponseEntity<Usuario> modificarUsuario(Usuario usuario);

    Rol registrarRol(Rol rol);

    Optional<Usuario> login(String email, String password);
}
