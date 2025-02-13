package com.postech.gerencie.pedidos.domain.enums;

public enum StatusPedido {

    INICIADO(1, "Pedido em aberto."),
    EM_SEPARACAO(2, "Em separação."),
    EM_ROTA(3, "Pedido a caminho da sua região."),
    SAIU_PARA_ENTREGA(4, "Pedido a caminho do seu endereço, fique atento à campainha!."),
    ENTREGA_CONCLUIDA(5, "Entrega concluída com sucesso."),
    CANCELADO(6, "Pedido cancelado.");

    private int id;
    private String descricaoPadrao;

    StatusPedido(int id, String descricaoPadrao) {
        this.id = id;
        this.descricaoPadrao = descricaoPadrao;
    }

    public static StatusPedido deId(int id) {
        for (var status : StatusPedido.values()) {
            if (status.id == id) {
                return status;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getDescricaoPadrao() {
        return descricaoPadrao;
    }
}
