package com.usuario.view;

import com.usuario.controller.AuthController;
import com.usuario.model.dto.LoginRegistroDTO;
import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.pojo.RespuestaMensajePojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante-shonatale/api")
public class UsuarioView {

    private final AuthController authController;

    public UsuarioView(AuthController authController) {
        this.authController = authController;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRegistroDTO loginRequestDTO) {
        return this.authController.login(loginRequestDTO);
    }
    @PostMapping("/usuario/registro")
    public ResponseEntity registrar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        return this.authController.registrar(usuarioRegistroDTO);
    }
    @GetMapping("/usuario/listar")
    public ResponseEntity listarUsuarios() throws Exception {
        return this.authController.listarUsuarios();
    }

    @PutMapping("/usuario/modificar")
    public ResponseEntity modificar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        return this.authController.modificar(usuarioRegistroDTO);
    }
}

