package com.postech.gerencie.pedidos.gateway.database.jpa.entities.external;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente_external")
public class ClienteExternal {

    @Id
    private Long id;

    @Column(name = "externalid")
    private Long externalId;

    @Column(name = "cpf", unique = true)
    private String cpf;

    public ClienteExternal(Long id, Long externalId, String cpf) {
        this.id = id;
        this.externalId = externalId;
        this.cpf = cpf;
    }

    public ClienteExternal(Long externalId, String cpf) {
        this.externalId = externalId;
        this.cpf = cpf;
    }

    protected ClienteExternal() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }
}
