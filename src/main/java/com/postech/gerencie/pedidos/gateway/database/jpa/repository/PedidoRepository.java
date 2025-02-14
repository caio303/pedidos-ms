package com.postech.gerencie.pedidos.gateway.database.jpa.repository;

import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByCpfCliente(String cpf);
}
