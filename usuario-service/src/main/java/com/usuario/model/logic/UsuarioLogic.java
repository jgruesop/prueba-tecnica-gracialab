package com.usuario.model.logic;

import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.pojo.ResponseMensajePojo;
import com.usuario.pojo.ResponseUsuarioPojo;
import com.usuario.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UsuarioLogic.class));
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private final IUsuarioService usuarioService;

    public UsuarioLogic(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> CrearToken() {

        //var auth = SecurityContextHolder.getContext().getAuthentication();
        //LOGGER.info("Datos del usuario: {} " + auth.getPrincipal());
        //LOGGER.info("Datos de los permisos: {} " + auth.getAuthorities());
        //LOGGER.info("Esta autenticado? : {} " + auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("Probando","spring boot security");
        return ResponseEntity.ok(mensaje);
    }

    public ResponseEntity validarUsuario( UsuarioRegistroDTO usuarioRegistroDTO) {
        Matcher mather = pattern.matcher(usuarioRegistroDTO.getEmail());
        try{
            if(Objects.isNull(usuarioRegistroDTO.getNombre()) || usuarioRegistroDTO.getNombre().isEmpty()) {
                return new ResponseEntity("Error al registrar los nombres", HttpStatus.BAD_REQUEST);
            }
            else if(Objects.isNull(usuarioRegistroDTO.getApellido()) || usuarioRegistroDTO.getApellido().isEmpty()) {
                return new ResponseEntity("Error al registrar los apellidos", HttpStatus.BAD_REQUEST);
            }
            else if(Objects.isNull(usuarioRegistroDTO.getEmail()) || usuarioRegistroDTO.getEmail().isEmpty()
                        || mather.find() == false) {
                return new ResponseEntity("Error al registrar el email", HttpStatus.BAD_REQUEST);
            }
            else if(Objects.isNull(usuarioRegistroDTO.getPassword()) || usuarioRegistroDTO.getPassword().isEmpty()) {
                return new ResponseEntity("Error al registrar el password", HttpStatus.BAD_REQUEST);
            }
            else if(Objects.isNull(usuarioRegistroDTO.getRol()) || usuarioRegistroDTO.getRol().isEmpty()) {
                return new ResponseEntity("Error al registrar el rol", HttpStatus.BAD_REQUEST);
            }
            else {
                //String passEncrypter = passwordEncoder().encode(password);
                if(validarExistenciaUsuarioPorEmail(usuarioRegistroDTO) == null) {
                    Rol rol = new Rol(usuarioRegistroDTO.getRol().trim().toLowerCase());
                    Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre().trim().toLowerCase(),
                            usuarioRegistroDTO.getApellido().trim().toLowerCase(),
                            usuarioRegistroDTO.getEmail().trim(),
                            usuarioRegistroDTO.getPassword().trim(), rol );

                    guardarRol(rol);
                    guardarUsuario(usuario);
                    return new ResponseEntity(HttpStatus.OK);
                }else {
                    return validarExistenciaUsuarioPorEmail(usuarioRegistroDTO);
                }

            }
        } catch (Exception e) {
            LOGGER.info("Se produjo un error: " + e.getMessage());
        }
        return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
    }

    public ResponseEntity<ResponseMensajePojo> validarExistenciaUsuarioPorEmail(UsuarioRegistroDTO usuarioRegistroDTO) {
        ResponseMensajePojo responseMensaje = new ResponseMensajePojo("El usuario ya exite");
        String email = usuarioService.buscarEmail(usuarioRegistroDTO.getEmail());
        if(email.matches(usuarioRegistroDTO.getEmail())) {
            return new ResponseEntity<>(responseMensaje, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    public Rol guardarRol(Rol rol) {
        return usuarioService.registrarRol(rol);
    }

    public ResponseEntity listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

}
