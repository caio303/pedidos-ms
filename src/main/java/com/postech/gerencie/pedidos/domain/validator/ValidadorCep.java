package com.postech.gerencie.pedidos.domain.validator;

public class ValidadorCep {

    private static final String CEP_REGEX = "^\\d{8}$";

    public boolean ehCEPValido(String cep) {
        return cep != null && cep.matches(CEP_REGEX);
    }

}
