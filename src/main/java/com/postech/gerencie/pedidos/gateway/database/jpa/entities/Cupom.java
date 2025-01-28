package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cupom")
public class Cupom {

    @Id
    private Long id;

    @Column(name = "chave", unique = true)
    private String chave;

    @Column(name = "porcentagemoff")
    private Float porcentagemOff;

    @Column(name = "limitedesconto")
    private Float limiteDesconto;

    public Cupom(Long id, String chave, Float porcentagemOff, Float limiteDesconto) {
        this.id = id;
        this.chave = chave;
        this.porcentagemOff = porcentagemOff;
        this.limiteDesconto = limiteDesconto;
    }

    protected Cupom() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Float getPorcentagemOff() {
        return porcentagemOff;
    }

    public void setPorcentagemOff(Float porcentagemOff) {
        this.porcentagemOff = porcentagemOff;
    }

    public Float getLimiteDesconto() {
        return limiteDesconto;
    }

    public void setLimiteDesconto(Float limiteDesconto) {
        this.limiteDesconto = limiteDesconto;
    }
}
