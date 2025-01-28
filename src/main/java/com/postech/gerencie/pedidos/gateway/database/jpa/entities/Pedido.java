package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private Long id;

    @Column(name = "clienteid")
    private Long clienteId;

    @JoinColumn(name = "cupom_id")
    @ManyToOne(targetEntity = Cupom.class, fetch = FetchType.LAZY)
    private Cupom cupom;


}
