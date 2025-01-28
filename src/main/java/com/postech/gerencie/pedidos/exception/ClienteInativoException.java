package com.postech.gerencie.pedidos.exception;

public class ClienteInativoException extends RuntimeException {
    public ClienteInativoException(String cpf) {
        super("Cliente Inativo: " + cpf);
    }
}
