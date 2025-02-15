package com.postech.gerencie.pedidos.controller.exceptionhandler;


import org.springframework.http.HttpStatus;

import java.util.Collection;

public record ErrorWithFields (int errorCode, String message, Collection<FieldValidationError> erros) {
    public ErrorWithFields(HttpStatus httpStatus, Collection<FieldValidationError> fieldErrors) {
        this(httpStatus.value(), httpStatus.getReasonPhrase(), fieldErrors);
    }

}