package com.usuario.service;

import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaUsuarioPojo;
import com.usuario.repository.IRolRepository;
import com.usuario.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service @Transactional @RequiredArgsConstructor @Slf4j
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
    public RespuestaUsuarioPojo buscarUsuario(String id) {
        Usuario usuarioBD = iUsuarioRepository.buscarIdUsuario(id);
        System.out.println("usuarioBD.getIdUsuario() = " + usuarioBD.getIdUsuario());
        RespuestaUsuarioPojo respuestaUsuarioPojo = new RespuestaUsuarioPojo();

        if(Objects.isNull(usuarioBD))
            mensajes.usuarioNoExiste();

        respuestaUsuarioPojo.setId(usuarioBD.getIdUsuario());
        respuestaUsuarioPojo.setNombre(usuarioBD.getNombre());
        respuestaUsuarioPojo.setApellido(usuarioBD.getApellido());
        respuestaUsuarioPojo.setEmail(usuarioBD.getEmail());
        respuestaUsuarioPojo.setEmail(usuarioBD.getPassword());
        respuestaUsuarioPojo.setRol(Collections.singleton(usuarioBD.getRoles()));

        return respuestaUsuarioPojo;
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

    @Override
    public ResponseEntity<Usuario> modificarUsuario(Usuario usuario) {
        Usuario usuarioDB = iUsuarioRepository.buscarIdUsuario(usuario.getIdUsuario().toString());
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
