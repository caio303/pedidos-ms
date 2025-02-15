package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    private Long id;

    @Column(name = "pedidoid", nullable = false)
    private Long pedidoId;

    @Column(name = "itemid", nullable = false)
    private Long itemId;


    @Column(name = "quantidade", nullable = false)
    private Double quantidade;

    public PedidoItem(Long id, Long pedidoId, Long itemId, Double quantidade) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.itemId = itemId;
        this.quantidade = quantidade;
    }

    public PedidoItem(Long pedidoId, Long itemId, Double quantidade) {
        this.pedidoId = pedidoId;
        this.itemId = itemId;
        this.quantidade = quantidade;
    }

    protected PedidoItem() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
