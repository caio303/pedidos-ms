package com.postech.gerencie.pedidos.gateway.database.jpa.repository;

import com.postech.gerencie.pedidos.gateway.database.jpa.entities.external.ClienteExternal;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.external.ItemExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemExternalRepository extends JpaRepository<ClienteExternal, Long> {

    Optional<ItemExternal> findByExternalId(Long externalId);

}
