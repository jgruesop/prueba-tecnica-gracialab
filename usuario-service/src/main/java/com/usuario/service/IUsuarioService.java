package com.usuario.service;

import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUsuarioService {

    ResponseEntity listarUsuarios();

    ResponseEntity buscarUsuario(Integer id);

    Usuario registrarUsuario(Usuario usuario);

    String buscarEmail(String email);

    ResponseEntity<Usuario> modificarUsuario(Usuario usuario);

    Rol registrarRol(Rol rol);

    Optional<Usuario> login(String email, String password);
}
