package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;

public record QuantidadeItem (
        Long itemId,
        Double quantidade
) {

    public QuantidadeItem(Long itemId, Double quantidade) {
        if (itemId == null) {
            throw new FormatoInvalidoException("itemId", itemId);
        }

        if (quantidade == null || quantidade <= 0) {
            throw new FormatoInvalidoException("quantidade", quantidade);
        }

        this.itemId = itemId;
        this.quantidade = quantidade;
    }
}
