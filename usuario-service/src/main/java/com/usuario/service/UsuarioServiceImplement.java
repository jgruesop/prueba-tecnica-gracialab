package com.usuario.service;

import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaLoginPojo;
import com.usuario.repository.IRolRepository;
import com.usuario.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service @Transactional @RequiredArgsConstructor
public class UsuarioServiceImplement implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioRepository iUsuarioRepository;
    private final IRolRepository rolRepository;
    private final Mensajes mensajes;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listarUsuarios() {
        if(!iUsuarioRepository.findAll().iterator().hasNext()) {
            return mensajes.noExistenResgistros();
        }
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public RespuestaLoginPojo findByIdUsuario(String id) {
        var usuarioBD = iUsuarioRepository.findByIdUsuario(id);
        RespuestaLoginPojo respuestaLoginPojo = new RespuestaLoginPojo();
        if(!usuarioBD.isEmpty()) {
        }
        for (Usuario usuario : usuarioBD) {
            respuestaLoginPojo.setId(usuario.getIdUsuario());
            respuestaLoginPojo.setEmail(usuario.getEmail());
            respuestaLoginPojo.setPassword(usuario.getPassword());
            respuestaLoginPojo.setRol(Collections.singleton(usuario.getRoles().getNombre()));
        }
        return respuestaLoginPojo;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public String buscarEmail(String email) {
        var usuario = iUsuarioRepository.findByEmail(email);
        return usuario.getEmail();
    }
/*
    @Override
    public ResponseEntity<Usuario> modificarUsuario(Usuario usuario) {
        var usuarioDB = iUsuarioRepository.findByIdUsuario(usuario.getIdUsuario());
        if (usuarioDB == null) {
            return null;
        }
        usuarioDB.setIdUsuario(usuario.getIdUsuario());
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setApellido(usuario.getApellido());
        usuarioDB.setEmail(usuario.getEmail());
        usuarioDB.setPassword(usuario.getPassword());
        usuarioRepository.save(usuarioDB);
        return mensajes.cambiosAplicadosExitosamente();
    }
*/
    @Override
    public Rol registrarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Usuario> login(String email, String password) {
        var usuario = usuarioRepository.findByEmail(email);
        return Optional.ofNullable(usuario);
    }
}
