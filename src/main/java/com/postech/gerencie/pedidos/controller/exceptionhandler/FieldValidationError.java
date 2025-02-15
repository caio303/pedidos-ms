package com.postech.gerencie.pedidos.controller.exceptionhandler;


import org.springframework.validation.FieldError;

public record FieldValidationError(String field, String message) {
    public FieldValidationError(FieldError fe) { this(fe.getField(), fe.getDefaultMessage()); }
}
