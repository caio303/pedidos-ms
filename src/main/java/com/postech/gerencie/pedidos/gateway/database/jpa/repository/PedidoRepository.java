package com.postech.gerencie.pedidos.gateway.database.jpa.repository;

import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
