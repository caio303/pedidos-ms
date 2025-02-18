package com.postech.gerencie.pedidos.usecase.dto;

import java.util.LinkedHashMap;

public record ItemComIdDTO(
        Long id,
        String nome,
        Double valor
) {

    public ItemComIdDTO(LinkedHashMap<String, Object> item) {
        this(
            Long.valueOf((Integer) item.get("id")),
            String.valueOf(item.get("nome")),
            Double.valueOf((String) item.get("valor"))
        );
    }
}
