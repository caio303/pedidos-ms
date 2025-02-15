package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;

public record Item(
        String nome,
        Double valor
) {

    public Item(String nome, Double valor) {
        if (nome == null || nome.isBlank()) {
            throw new FormatoInvalidoException("nome", nome);
        }

        if (valor == null || valor < 0) {
            throw new FormatoInvalidoException("valor", valor);
        }

        this.nome = nome;
        this.valor = valor;
    }
}
