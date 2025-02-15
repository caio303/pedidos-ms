package com.postech.gerencie.pedidos.usecase.dto.novopedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public record NovoPedidoDTO (
        @NotBlank String cpfCliente,
        @NotNull @NotEmpty Collection<QuantidadeItemDTO> quantidadeItemDTOS,
        String cupomAplicado,
        @NotBlank String cepEntrega
) {

    @JsonIgnore
    public Map<Long, Double> getItemQuantidadeMap() {
        return this.quantidadeItemDTOS
                .stream()
                .collect(Collectors.toMap(QuantidadeItemDTO::itemId, QuantidadeItemDTO::quantidade));
    }
}
