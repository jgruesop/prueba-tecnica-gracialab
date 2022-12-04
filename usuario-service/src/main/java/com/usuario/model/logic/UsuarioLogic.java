package com.usuario.model.logic;

import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.model.util.MensajesError;
import com.usuario.pojo.RespuestaMensajePojo;
import com.usuario.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

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
    private final MensajesError mensajesError;
    private final PasswordEncoder passwordEncoder;

    public UsuarioLogic(IUsuarioService usuarioService, MensajesError mensajesError, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.mensajesError = mensajesError;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity validarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Matcher mather = pattern.matcher(usuarioRegistroDTO.getEmail());
        try{
            if(Objects.isNull(usuarioRegistroDTO.getNombre()) || usuarioRegistroDTO.getNombre().isEmpty()) {
                return mensajesError.validarInformacionNombres();
            }
            else if(Objects.isNull(usuarioRegistroDTO.getApellido()) || usuarioRegistroDTO.getApellido().isEmpty()) {
                return mensajesError.validarInformacionApellidos();
            }
            else if(Objects.isNull(usuarioRegistroDTO.getEmail()) || usuarioRegistroDTO.getEmail().isEmpty()
                        || mather.find() == false) {
                return mensajesError.validarInformacionEmail();
            }
            else if(Objects.isNull(usuarioRegistroDTO.getPassword()) || usuarioRegistroDTO.getPassword().isEmpty()) {
                return mensajesError.validarInformacionPassword();
            }
            else if(Objects.isNull(usuarioRegistroDTO.getRol()) || usuarioRegistroDTO.getRol().isEmpty()) {
                return mensajesError.validarInformacionRol();
            }
            else {
                if(validarExistenciaUsuarioPorEmail(usuarioRegistroDTO.getEmail()) == null) {
                    Rol rol = new Rol(usuarioRegistroDTO.getRol().trim().toLowerCase());
                    guardarRol(rol);
                    guardarUsuario(Usuario.of(usuarioRegistroDTO.getNombre().trim().toLowerCase(),
                            usuarioRegistroDTO.getApellido().trim().toLowerCase(),
                            usuarioRegistroDTO.getEmail().trim(),
                            passwordEncoder.encode(usuarioRegistroDTO.getPassword()), rol ));
                    return mensajesError.registroExitoso();
                }else {
                    return validarExistenciaUsuarioPorEmail(usuarioRegistroDTO.getEmail());
                }

            }
        } catch (Exception e) {
            LOGGER.info("Se produjo un error: " + e.getMessage());
        }
        return new ResponseEntity<>("Error interno.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RespuestaMensajePojo> validarExistenciaUsuarioPorEmail(String email) {
        RespuestaMensajePojo responseMensaje = new RespuestaMensajePojo("El usuario ya exite");
        String emailBD = usuarioService.buscarEmail(email);
        if(email.equals(emailBD)) {
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
