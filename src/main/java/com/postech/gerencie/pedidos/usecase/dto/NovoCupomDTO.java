package com.postech.gerencie.pedidos.usecase.dto;

public record NovoCupomDTO(
        String chave,
        double porcentagemOff,
        Double limiteDesconto
) {
}
