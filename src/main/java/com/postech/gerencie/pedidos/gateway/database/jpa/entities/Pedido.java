package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private Long id;

    @Column(name = "cpfcliente", nullable = false)
    private String cpfCliente;

    @Column(name = "cupomid")
    private Long cupomId;

    @Column(name = "cepentrega")
    private String cepEntrega;

    @Column(name = "statusid", nullable = false)
    private Integer statusId;

    @Column(name = "valortotal", nullable = false)
    private Float valorTotal;

    @Column(name = "codigorastreio")
    private String codigoRastreio;

    @CreatedDate
    @Column(name = "datacriacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "dataatualizacao")
    private LocalDateTime dataAtualizacao;

    public Pedido(Long id, String cpfCliente, Long cupomId, String cepEntrega, Integer statusId, Float valorTotal, String codigoRastreio, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cupomId = cupomId;
        this.cepEntrega = cepEntrega;
        this.statusId = statusId;
        this.valorTotal = valorTotal;
        this.codigoRastreio = codigoRastreio;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getCupomId() {
        return cupomId;
    }

    public void setCupomId(Long cupomId) {
        this.cupomId = cupomId;
    }

    public String getCepEntrega() {
        return cepEntrega;
    }

    public void setCepEntrega(String cepEntrega) {
        this.cepEntrega = cepEntrega;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
