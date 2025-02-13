package com.postech.gerencie.pedidos.exception;

public class CupomInexistenteException extends RuntimeException {
    public CupomInexistenteException(String chave) {
        super("Cupom inexistente: " + chave);
    }
}
