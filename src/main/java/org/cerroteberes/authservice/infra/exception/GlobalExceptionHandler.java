package org.cerroteberes.authservice.infra.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        if (e instanceof FeignException.Unauthorized) {
            return new ResponseEntity<>("Error 400: Solicitud incorrecta", HttpStatus.BAD_REQUEST);
        } else if (e instanceof FeignException.InternalServerError) {
            return new ResponseEntity<>("Error 500: Error interno del servidor" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>("Error desconocido: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
