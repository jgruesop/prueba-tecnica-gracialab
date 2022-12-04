package com.usuario.service;

import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaUsuarioPojo;
import com.usuario.repository.IRolRepository;
import com.usuario.repository.IUsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioRepository iUsuarioRepository;
    private final IRolRepository rolRepository;
    private final Mensajes mensajes;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImplement(IUsuarioRepository usuarioRepository, IUsuarioRepository iUsuarioRepository, IRolRepository rolRepository, Mensajes mensajes, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.iUsuarioRepository = iUsuarioRepository;
        this.rolRepository = rolRepository;
        this.mensajes = mensajes;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listarUsuarios() {
        if(!iUsuarioRepository.findAll().iterator().hasNext()) {
            return mensajes.noExistenResgistros();
        }
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity buscarUsuario(Integer id) {
        List<Usuario> resultado = iUsuarioRepository.buscarIdUsuario(id);
        RespuestaUsuarioPojo respuestaUsuarioPojo = new RespuestaUsuarioPojo();
        if(!resultado.isEmpty()) {
            for(Usuario usuario : resultado) {
                respuestaUsuarioPojo.setId(usuario.getId());
                respuestaUsuarioPojo.setNombre(usuario.getNombre());
                respuestaUsuarioPojo.setApellido(usuario.getApellido());
                respuestaUsuarioPojo.setEmail(usuario.getEmail());
                respuestaUsuarioPojo.setRol(String.valueOf((usuario.getRoles())));
            }
        }else {
            return mensajes.usuarioNoExiste();
        }
        return new ResponseEntity(respuestaUsuarioPojo, HttpStatus.OK);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarEmail(String email) {
        return iUsuarioRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<Usuario> modificarUsuario(Usuario usuario) {
        Usuario usuarioDB = (Usuario) iUsuarioRepository.buscarIdUsuario(usuario.getId());
        if (usuarioDB == null) {
            return null;
        }
        usuarioDB.setId(usuario.getId());
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
        return usuario;
    }
}
