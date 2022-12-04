package com.usuario.model.util;

import com.usuario.pojo.RespuestaMensajePojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MensajesError {

    public static final String ERROR_AL_REGISTRAR_CAMPO_NOMBRE    = "Error al registrar el campo nombres";
    public static final String ERROR_AL_REGISTRAR_CAMPO_APELLIDOS = "Error al registrar el campo apellidos";
    public static final String ERROR_AL_VALIDAR_EL_CORREO         = "Error al validar el correo electrónico";
    public static final String ERROR_AL_REGISTRAR_EL_PASSWORD     = "Error al registrar el password";
    public static final String ERROR_AL_REGISTRAR_CAMPO_ROL       = "Error al registrar el campo rol";
    public static final String REGISTRO_EXITOSO                   = "Registro exitoso";


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

}
