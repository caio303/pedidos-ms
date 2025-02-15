package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.domain.validator.ValidadorCep;
import com.postech.gerencie.pedidos.domain.validator.ValidadorCpf;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.exception.PedidoSemItensException;

import java.time.LocalDateTime;
import java.util.Collection;

public record Pedido(
        String cpfCliente,
        StatusPedido status,
        String descricao,
        Collection<QuantidadeItem> itens,
        String cupomAplicado,
        Double valorTotal,
        String cepEntrega,
        String codigoRastreio,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
    public Pedido(String cpfCliente, StatusPedido status, String descricao, Collection<QuantidadeItem> itens, String cupomAplicado, Double valorTotal, String cepEntrega, String codigoRastreio, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        var validadorCpf = new ValidadorCpf();
        if (!validadorCpf.ehCPFValido(cpfCliente)) {
            throw new FormatoInvalidoException("cpf", cpfCliente);
        }

        var validadorCep = new ValidadorCep();
        if (!validadorCep.ehCEPValido(cepEntrega)) {
            throw new FormatoInvalidoException("cep", cepEntrega);
        }

        if (status == null) {
            throw new FormatoInvalidoException("status", null);
        }

        if (itens == null || itens.isEmpty()) {
            throw new PedidoSemItensException();
        }

        if (valorTotal == null || valorTotal < 0) {
            throw new FormatoInvalidoException("valorTotal", valorTotal);
        }

        this.cpfCliente = cpfCliente;
        this.status = status;
        this.descricao = descricao;
        this.itens = itens;
        this.cupomAplicado = cupomAplicado;
        this.valorTotal = valorTotal;
        this.cepEntrega = cepEntrega;
        this.codigoRastreio = codigoRastreio;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}
