package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.exception.DescontoInvalidoException;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;

public class Cupom {

    private String chave;
    private double porcentagemOff;
    private Double limiteDesconto;

    public Cupom(String chave, double porcentagemOff) {
        this(chave, porcentagemOff, null);
    }

    public Cupom(String chave, double porcentagemOff, Double limiteDesconto) {
        if (chave == null || chave.isBlank()) {
            throw new FormatoInvalidoException("chave", chave);
        }

        if (porcentagemOff <= 0 || porcentagemOff > 1) {
            throw new DescontoInvalidoException("Porcentagem de desconto tem que estar entre 0 e 1");
        }

        if (limiteDesconto != null && limiteDesconto < 0) {
            throw new FormatoInvalidoException("limiteDesconto", limiteDesconto);
        }

        if (limiteDesconto != null && limiteDesconto > 0) {
            this.limiteDesconto = limiteDesconto;
        } else {
            this.limiteDesconto = null;
        }

        this.chave = chave;
        this.porcentagemOff = porcentagemOff;
    }

    public Double descontar(Double valor) {
        if (valor <= 0) {
            return 0d;
        }

        var desconto = valor * porcentagemOff;
        if (limiteDesconto != null && desconto > limiteDesconto) {
            return valor - limiteDesconto;
        }

        var valorDescontado = valor - desconto;

        if (valorDescontado < 0) {
            return 0d;
        }

        return valorDescontado;
    }

    public String getChave() {
        return chave;
    }

    public double getPorcentagemOff() {
        return porcentagemOff;
    }

    public Double getLimiteDesconto() {
        return limiteDesconto;
    }
}
