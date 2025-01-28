package com.postech.gerencie.pedidos.exception;

public class ClienteNaoCadastradoException extends RuntimeException {
    public ClienteNaoCadastradoException(String cpf) {
        super("Cliente não cadastrado: " + cpf);
    }
}
