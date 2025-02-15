package com.postech.gerencie.pedidos.gateway.database.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpfcliente", nullable = false)
    private String cpfCliente;

    @ManyToOne(targetEntity = Cupom.class)
    @JoinColumn(name = "cupomid", referencedColumnName = "id")
    private Cupom cupom;

    @Column(name = "cepentrega")
    private String cepEntrega;

    @Column(name = "statusid", nullable = false)
    private Integer statusId;

    @Column(name = "valortotal", nullable = false)
    private Double valorTotal;

    @Column(name = "codigorastreio")
    private String codigoRastreio;

    @CreatedDate
    @Column(name = "datacriacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "dataatualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "pedidoId", targetEntity = PedidoItem.class, fetch = FetchType.LAZY)
    private List<PedidoItem> itensPedido;

    public Pedido(Long id, String cpfCliente, Long cupomId, String cepEntrega, Integer statusId, Double valorTotal, String codigoRastreio, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, List<PedidoItem> itensPedido) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cupom = new Cupom(cupomId, null, null, null);
        this.cepEntrega = cepEntrega;
        this.statusId = statusId;
        this.valorTotal = valorTotal;
        this.codigoRastreio = codigoRastreio;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.itensPedido = itensPedido;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
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

    public List<PedidoItem> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoItem> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }
}
