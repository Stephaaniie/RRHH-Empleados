package ar.com.ada.api.empleado.empleado.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<CustomHttpResponse> handleResourceNotFoundExceptions(ResourceNotFoundException ex) {
        CustomHttpResponse err = new CustomHttpResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}