package com.postech.gerencie.pedidos.controller.exceptionhandler;

import com.postech.gerencie.pedidos.exception.BaseHttpMappedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PedidosExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PedidosExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldValidationError::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleBadRequest2(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity handleBadRequest3(HandlerMethodValidationException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST, "Bad Request: Verificar formatações do corpo da requisição"));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity notFoundException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(HttpStatus.FORBIDDEN, "Acesso negado"));
    }

    @ExceptionHandler(BaseHttpMappedException.class)
    public ResponseEntity handleException(BaseHttpMappedException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new Error(ex.getHttpStatus(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error("Erro interno: {}", ex.getLocalizedMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Ocorreu um erro interno, entre em contato ou tente mais tarde..."));
    }

    private record Error (Integer errorCode, String message) {
        public Error(HttpStatus httpStatus, String message) { this(httpStatus.value(), message); }
        public Error(String message) {	this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message); }
    }

    private record FieldValidationError(String field, String message) {
        public FieldValidationError(FieldError fe) { this(fe.getField(), fe.getDefaultMessage()); }
    }
}
