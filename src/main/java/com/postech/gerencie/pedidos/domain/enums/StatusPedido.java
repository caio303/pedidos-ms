package com.postech.gerencie.pedidos.domain.enums;

public enum StatusPedido {

    EM_ABERTO(1, "Pedido em aberto."),
    PROCESSANDO_PAGAMENTO(2, "Pagamento sendo processado."),
    CANCELADO(3, "Pedido cancelado."),
    CONCLUIDO(4, "Pedido concluído com sucesso.");

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

    public String getDescricaoPadrao() {
        return descricaoPadrao;
    }
}
