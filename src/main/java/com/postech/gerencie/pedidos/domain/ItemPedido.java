package com.postech.gerencie.pedidos.domain;

public record ItemPedido(
        Item item,
        Integer quantidade
) {
}
