package com.postech.gerencie.pedidos.gateway.database.jpa.entities.external;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_external")
public class ItemExternal {

    @Id
    private Long id;

    @Column(name = "externalitemid", unique = true)
    private Long externalIId;

    public ItemExternal(Long id, Long externalIId) {
        this.id = id;
        this.externalIId = externalIId;
    }

    protected ItemExternal() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalIId() {
        return externalIId;
    }

    public void setExternalIId(Long externalItemId) {
        this.externalIId = externalItemId;
    }
}
