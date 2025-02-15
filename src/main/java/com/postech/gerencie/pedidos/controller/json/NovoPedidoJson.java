package com.postech.gerencie.pedidos.controller.json;

import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Collection;

public record NovoPedidoJson(
        @NotBlank @CPF String cpfCliente,
        @NotNull @NotEmpty Collection<@Valid QuantidadeItemJson> quantidadeItemDTOS,
        String cupomAplicado,
        @NotBlank String cepEntrega
) {

    public NovoPedidoDTO toDTO() {
        var qtdDTOs = quantidadeItemDTOS.stream().map(QuantidadeItemJson::toDTO).toList();
        return new NovoPedidoDTO(cpfCliente, qtdDTOs, cupomAplicado, cepEntrega);
    }

}
