package com.usuario.service;

import com.usuario.model.dao.IUsuarioDao;
import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.pojo.RespuestaUsuarioPojo;
import com.usuario.repository.IRolRepository;
import com.usuario.repository.IUsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioDao iUsuarioDao;
    private final IRolRepository rolRepository;

    public UsuarioServiceImplement(IUsuarioRepository usuarioRepository, IUsuarioDao iUsuarioDao, IRolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.iUsuarioDao = iUsuarioDao;
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listarUsuarios() {
        if(!iUsuarioDao.findAll().iterator().hasNext()) {
            return new ResponseEntity("No existen registros", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity buscarUsuario(Integer id) {
        List<Usuario> resultado = iUsuarioDao.buscarIdUsuario(id);
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
            return new ResponseEntity("Usuario no existe", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(respuestaUsuarioPojo, HttpStatus.OK);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public String buscarEmail(String email) {
        return iUsuarioDao.buscarEmail(email);
    }

    @Override
    public ResponseEntity<Usuario> modificarUsuario(Usuario usuario) {
        Usuario usuarioDB = (Usuario) iUsuarioDao.buscarIdUsuario(usuario.getId());
        if (usuarioDB == null) {
            return null;
        }
        usuarioDB.setId(usuario.getId());
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setApellido(usuario.getApellido());
        usuarioDB.setEmail(usuario.getEmail());
        usuarioDB.setPassword(usuario.getPassword());
        usuarioRepository.save(usuarioDB);
        return new ResponseEntity("Cambios aplicados exitosamente", HttpStatus.OK);
    }

    @Override
    public Rol registrarRol(Rol rol) {
        return rolRepository.save(rol);
    }
}
