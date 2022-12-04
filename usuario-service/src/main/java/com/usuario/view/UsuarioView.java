package com.usuario.view;

import com.usuario.controller.AuthController;
import com.usuario.model.dto.UsuarioRegistroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante-shonatale/api")
public class UsuarioView {

    private final AuthController authController;

    public UsuarioView(AuthController authController) {
        this.authController = authController;
    }



    @GetMapping("/listar-usuarios")
    public ResponseEntity listarUsuarios() throws Exception {
        return this.authController.listarUsuarios();
    }
    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
            return this.authController.registrar(usuarioRegistroDTO);
    }
    @PutMapping("/usuarios")
    public ResponseEntity modificar(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        return this.authController.modificar(usuarioRegistroDTO);
    }
}

