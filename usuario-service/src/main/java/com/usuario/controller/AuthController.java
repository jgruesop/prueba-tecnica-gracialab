package com.usuario.controller;

import com.usuario.model.dto.LoginRegistroDTO;
import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.model.logic.UsuarioLogic;
import com.usuario.pojo.RespuestaUsuarioPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthController {
    private final UsuarioLogic usuarioLogic;

    public AuthController(UsuarioLogic usuarioLogic) {
        this.usuarioLogic = usuarioLogic;
    }

    public ResponseEntity login(@RequestBody LoginRegistroDTO loginRequestDTO, HttpServletResponse response) {
        return usuarioLogic.login(loginRequestDTO, response);
    }
/*
    public RespuestaUsuarioPojo usuario(HttpServletRequest request) {
        return usuarioLogic.acceso(request);
    }*/
    public ResponseEntity listarUsuarios() {
        return usuarioLogic.listarUsuarios();
    }

    public ResponseEntity registrar( UsuarioRegistroDTO usuarioRegistroDTO) {
        return usuarioLogic.validarUsuario(usuarioRegistroDTO);
    }

    public ResponseEntity modificar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        return usuarioLogic.validarUsuario(usuarioRegistroDTO);
    }
}
