package com.postech.gerencie.pedidos.domain;

public class Cupom {

    private String chave;
    private Double porcentagemOff;
    private Double limiteDesconto;

    public Cupom(String chave, Double porcentagemOff) {
        this.chave = chave;
        this.porcentagemOff = porcentagemOff;
    }

    public Cupom(String chave, Double porcentagemOff, Double limiteDesconto) {
        this.chave = chave;
        this.porcentagemOff = porcentagemOff;
        this.limiteDesconto = limiteDesconto;
    }

    public Double descontar(Double valor) {
        if (valor <= 0) {
            return 0d;
        }

        var valorDescontado = valor * porcentagemOff;
        if (limiteDesconto != null && valorDescontado > limiteDesconto) {
            return valor - limiteDesconto;
        }

        return valor - valorDescontado;
    }

}
