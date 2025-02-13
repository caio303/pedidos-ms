package com.postech.gerencie.pedidos.gateway.database.jpa.repository;

import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    Optional<Cupom> findByChaveIgnoreCase(String chave);
}
