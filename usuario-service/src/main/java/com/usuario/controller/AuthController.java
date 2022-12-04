package com.usuario.controller;

import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.model.logic.UsuarioLogic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public class AuthController {
    private final UsuarioLogic usuarioLogic;

    public AuthController(UsuarioLogic usuarioLogic) {
        this.usuarioLogic = usuarioLogic;
    }

    public String login() {
        return "Hello World!";
    }
    public ResponseEntity listarUsuarios() {
        return this.usuarioLogic.listarUsuarios();
    }
    public ResponseEntity registrar( UsuarioRegistroDTO usuarioRegistroDTO) {
        return this.usuarioLogic.validarUsuario(usuarioRegistroDTO);
    }
    public ResponseEntity modificar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        return this.usuarioLogic.validarUsuario(usuarioRegistroDTO);
    }
}
