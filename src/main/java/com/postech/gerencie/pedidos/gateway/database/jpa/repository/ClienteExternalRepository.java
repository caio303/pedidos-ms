package com.postech.gerencie.pedidos.gateway.database.jpa.repository;

import com.postech.gerencie.pedidos.gateway.database.jpa.entities.external.ClienteExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteExternalRepository extends JpaRepository<ClienteExternal, Long> {

    Optional<ClienteExternal> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
