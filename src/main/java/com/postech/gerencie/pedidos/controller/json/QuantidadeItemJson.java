package com.postech.gerencie.pedidos.controller.json;

import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record QuantidadeItemJson(
        @NotNull Long itemId,
        @NotNull @Positive Double quantidade
) {
    public QuantidadeItemDTO toDTO() {
        return new QuantidadeItemDTO(itemId, quantidade);
    }
}
