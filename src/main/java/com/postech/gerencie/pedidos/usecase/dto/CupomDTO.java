package com.postech.gerencie.pedidos.usecase.dto;

public record CupomDTO (
        String chave,
        Double porcentagemOff,
        Double limiteDesconto
) { }
