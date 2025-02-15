package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chave", unique = true)
    private String chave;

    @Column(name = "porcentagemoff")
    private Double porcentagemOff;

    @Column(name = "limitedesconto")
    private Double limiteDesconto;

    public Cupom(Long id, String chave, Double porcentagemOff, Double limiteDesconto) {
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

    public Double getPorcentagemOff() {
        return porcentagemOff;
    }

    public void setPorcentagemOff(Double porcentagemOff) {
        this.porcentagemOff = porcentagemOff;
    }

    public Double getLimiteDesconto() {
        return limiteDesconto;
    }

    public void setLimiteDesconto(Double limiteDesconto) {
        this.limiteDesconto = limiteDesconto;
    }
}
