package com.postech.gerencie.pedidos.controller.exceptionhandler;

import com.postech.gerencie.pedidos.exception.BaseHttpMappedException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Hidden
@RestControllerAdvice
public class PedidosExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PedidosExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorWithFields> handleBadRequest(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log(HttpStatus.BAD_REQUEST, req.getRequestURI());
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(
                new ErrorWithFields(HttpStatus.BAD_REQUEST, errors.stream().map(FieldValidationError::new).toList())
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleBadRequest2(HttpServletRequest req, HttpMessageNotReadableException ex) {
        log(HttpStatus.BAD_REQUEST, req.getRequestURI());
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorWithFields> handleBadRequest3(HttpServletRequest req, HandlerMethodValidationException ex) {
        log(HttpStatus.BAD_REQUEST, req.getRequestURI());

        var fieldErrors = new ArrayList<FieldValidationError>();
        for (var validation : ex.getValueResults()) {
            var argument = validation.getArgument();
            List<MessageSourceResolvable> resolvableErrors = validation.getResolvableErrors();
            var resolvable = resolvableErrors
                    .stream()
                    .map(erro -> new FieldValidationError(argument.toString(), erro.getDefaultMessage()))
                    .toList();
            fieldErrors.addAll(resolvable);
        }

        return ResponseEntity.badRequest().body(new ErrorWithFields(HttpStatus.BAD_REQUEST, fieldErrors));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> notFoundException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(HttpStatus.FORBIDDEN, "Acesso negado"));
    }

    @ExceptionHandler(BaseHttpMappedException.class)
    public ResponseEntity<Error> handleException(HttpServletRequest req, BaseHttpMappedException ex) {
        log(ex.getHttpStatus(), req.getRequestURI());
        return ResponseEntity.status(ex.getHttpStatus()).body(new Error(ex.getHttpStatus(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        log.error("Erro interno: {}", ex.getLocalizedMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Ocorreu um erro interno, entre em contato ou tente mais tarde..."));
    }

    private void log(HttpStatus status, String uri) {
        log.warn("{}: URL={}", status.name(), uri);
    }

}
