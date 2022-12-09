package com.usuario.model.logic;

import com.usuario.model.dto.LoginRegistroDTO;
import com.usuario.model.dto.UsuarioRegistroDTO;
import com.usuario.model.entity.Rol;
import com.usuario.model.entity.Usuario;
import com.usuario.model.util.MensajeToken;
import com.usuario.model.util.Mensajes;
import com.usuario.pojo.RespuestaMensajePojo;
import com.usuario.pojo.RespuestaUsuarioPojo;
import com.usuario.service.IUsuarioService;
import com.usuario.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
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
    private final Mensajes mensajes;
    private final PasswordEncoder passwordEncoder;
    private final MensajeToken mensajeToken;
    private final TokenService tokens;


    public UsuarioLogic(IUsuarioService usuarioService, Mensajes mensajes,
                        PasswordEncoder passwordEncoder, MensajeToken mensajeToken, TokenService tokens) {
        this.usuarioService = usuarioService;
        this.mensajes = mensajes;
        this.passwordEncoder = passwordEncoder;
        this.mensajeToken = mensajeToken;
        this.tokens = tokens;
    }

    public ResponseEntity validarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Matcher mather = pattern.matcher(usuarioRegistroDTO.getEmail());        
        try{
            if(Objects.isNull(usuarioRegistroDTO.getNombre()) || usuarioRegistroDTO.getNombre().isEmpty()) {
                return mensajes.validarInformacionNombres();
            }
            if(Objects.isNull(usuarioRegistroDTO.getApellido()) || usuarioRegistroDTO.getApellido().isEmpty()) {
                return mensajes.validarInformacionApellidos();
            }
            if(Objects.isNull(usuarioRegistroDTO.getEmail()) || usuarioRegistroDTO.getEmail().isEmpty() || !mather.find()) {
                return mensajes.validarInformacionEmail();
            }
            if(Objects.isNull(usuarioRegistroDTO.getPassword()) || usuarioRegistroDTO.getPassword().isEmpty()) {
                return mensajes.validarInformacionPassword();
            }
            if(Objects.isNull(usuarioRegistroDTO.getRol()) || usuarioRegistroDTO.getRol().isEmpty()) {
                return mensajes.validarInformacionRol();
            }
            if(validarExistenciaUsuarioPorEmail(usuarioRegistroDTO.getEmail()) == null) {
                Rol rol = new Rol(usuarioRegistroDTO.getRol().trim().toLowerCase());
                guardarRol(rol);
                guardarUsuario(Usuario.of(usuarioRegistroDTO.getNombre().trim().toLowerCase(),
                        usuarioRegistroDTO.getApellido().trim().toLowerCase(),
                        usuarioRegistroDTO.getEmail().trim(),
                        passwordEncoder.encode(usuarioRegistroDTO.getPassword()), rol));
                return mensajes.registroExitoso();
            }

            return validarExistenciaUsuarioPorEmail(usuarioRegistroDTO.getEmail());

        } catch (Exception e) {
            LOGGER.info("Se produjo un error: " + e.getMessage());
        }
        return new ResponseEntity<>("Error interno.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RespuestaMensajePojo> validarExistenciaUsuarioPorEmail(String email) {
        RespuestaMensajePojo responseMensaje = new RespuestaMensajePojo("El usuario ya exite");
        String emailBD = (usuarioService.buscarEmail(email));
        System.out.println("emailBD = " + emailBD);
        if(email.matches(emailBD)) {
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

    public ResponseEntity login(LoginRegistroDTO loginRegistroDTO, HttpServletResponse response) {

        var usuario = usuarioService.login(loginRegistroDTO.getEmail(), loginRegistroDTO.getPassword())
                .orElse(null);

        if(Objects.isNull(usuario))
            return mensajes.credencialesNoCoinciden();

        if(!passwordEncoder.matches(loginRegistroDTO.getPassword(), usuario.getPassword()))
            return mensajes.credencialesNoCoinciden();

        if(!usuario.getRoles().getNombre().equals("ADMIN") && !usuario.getRoles().getNombre().equals("admin"))
            return mensajes.usuarioSinPermisos();

        String token = tokens.crearToken(usuario.getIdUsuario().toString());

        Cookie cookie = new Cookie("token", tokens.getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/restaurante-shonatale/api");
        response.addCookie(cookie);

        return mensajeToken.loginExitoso(token);
    }

    public RespuestaUsuarioPojo acceso(HttpServletRequest request) {
        var usuario = (Usuario) request.getAttribute("usuario");
        return new RespuestaUsuarioPojo(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(),usuario.getPassword(), Collections.singleton(usuario.getRoles().getNombre()));
    }
}
