package com.postech.gerencie.pedidos.domain.validator;

public class ValidadorCpf {

    private static final String CPF_REGEX = "^\\d{11}$";

    public boolean ehCPFValido(String cpf) {
        return cpf != null && cpf.matches(CPF_REGEX);
    }
}
