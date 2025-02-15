package com.postech.gerencie.pedidos.controller.json;

import com.postech.gerencie.pedidos.usecase.dto.NovoCupomDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

public record NovoCupomJson(
        @NotBlank String chave,
        @NotNull @Range(min = 0, max = 1) Double porcentagemOff,
        @PositiveOrZero Double limiteDesconto
) {

    public NovoCupomDTO toDTO() {
        return new NovoCupomDTO(chave, porcentagemOff, limiteDesconto);
    }

}
