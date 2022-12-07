package com.usuario.model.util;

import com.usuario.pojo.RespuestaMensajePojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Mensajes {

    public static final String ERROR_AL_REGISTRAR_CAMPO_NOMBRE    = "Error al registrar el campo nombres";
    public static final String ERROR_AL_REGISTRAR_CAMPO_APELLIDOS = "Error al registrar el campo apellidos";
    public static final String ERROR_AL_VALIDAR_EL_CORREO         = "Error al validar el correo electrónico";
    public static final String ERROR_AL_REGISTRAR_EL_PASSWORD     = "Error al registrar el password";
    public static final String ERROR_AL_REGISTRAR_CAMPO_ROL       = "Error al registrar el campo rol";
    public static final String REGISTRO_EXITOSO                   = "Registro exitoso";

    public static final String ERROR_TOKEN                        = "Error en el token";
    public static final String ERROR_TOKEN_BARRER                 = "Error en el token del portador";
    public static final String CREDENCIALES_NO_COINCIDEN          = "Credenciales invalidas";
    public static final String CAMBIOS_APLICADOS_EXITOSAMENTE     = "Cambios aplicados exitosamente";
    public static final String USUARIO_NO_EXISTE                  = "Usuario no existe";
    public static final String NO_EXISTEN_REGISTROS               = "No existen registros";
    public static final String USUARIO_SIN_PERMISOS               = "Usuario sin permisos";


    public ResponseEntity errorToken() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_TOKEN);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity noExistenResgistros() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(NO_EXISTEN_REGISTROS);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity usuarioNoExiste() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(USUARIO_NO_EXISTE);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity cambiosAplicadosExitosamente() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(CAMBIOS_APLICADOS_EXITOSAMENTE);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.CREATED);
    }
    public ResponseEntity credencialesNoCoinciden() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(CREDENCIALES_NO_COINCIDEN);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity registroExitoso() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(REGISTRO_EXITOSO);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.CREATED);
    }
    public ResponseEntity validarInformacionNombres() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_AL_REGISTRAR_CAMPO_NOMBRE);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity validarInformacionApellidos() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_AL_REGISTRAR_CAMPO_APELLIDOS);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity validarInformacionEmail() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_AL_VALIDAR_EL_CORREO);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity validarInformacionPassword() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_AL_REGISTRAR_EL_PASSWORD);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity validarInformacionRol() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_AL_REGISTRAR_CAMPO_ROL);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.BAD_REQUEST);
    }
    public ResponseStatusException errorTokenBearer() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(ERROR_TOKEN_BARRER);
        return new ResponseStatusException(HttpStatus.FORBIDDEN, respuestaMensajePojo.toString());
    }
    public ResponseEntity usuarioSinPermisos() {
        RespuestaMensajePojo respuestaMensajePojo = new RespuestaMensajePojo(USUARIO_SIN_PERMISOS);
        return new ResponseEntity(respuestaMensajePojo, HttpStatus.UNAUTHORIZED);
    }
}
