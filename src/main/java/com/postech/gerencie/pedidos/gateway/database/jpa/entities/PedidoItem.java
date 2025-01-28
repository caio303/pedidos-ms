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

    @Column(name = "pedidoid")
    private Long pedidoId;

    @Column(name = "itemid")
    private Long itemId;

    public PedidoItem(Long id, Long pedidoId, Long itemId) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.itemId = itemId;
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
}
