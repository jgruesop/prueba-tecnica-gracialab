package com.usuario.model.util;

import com.usuario.pojo.RespuestaTokenPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MensajeToken {

    public ResponseEntity loginExitoso(String token) {
        RespuestaTokenPojo respuestaTokenPojo = new RespuestaTokenPojo(token);
        return new ResponseEntity(respuestaTokenPojo, HttpStatus.OK);
    }
}
