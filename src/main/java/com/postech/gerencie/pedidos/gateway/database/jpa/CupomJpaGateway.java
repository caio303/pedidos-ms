package com.postech.gerencie.pedidos.gateway.database.jpa;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.database.jpa.mapper.CupomMapper;
import com.postech.gerencie.pedidos.gateway.database.jpa.repository.CupomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CupomJpaGateway implements CupomGateway {

    private static final Logger log = LoggerFactory.getLogger(CupomJpaGateway.class);
    private final CupomRepository cupomRepository;

    private final CupomMapper cupomMapper = new CupomMapper();

    public CupomJpaGateway(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @Override
    public void criarCupom(String chave, double porcentagemOff, Double limiteDesconto) {
        var cupomEntity = cupomMapper.toEntity(chave, porcentagemOff, limiteDesconto);
        cupomRepository.save(cupomEntity);
    }

    @Override
    public void excluirCupom(String chave) {

    }

    @Override
    public Cupom buscarPorChave(String chave) {
        var cupomOptional = cupomRepository.findByChaveIgnoreCase(chave);

        if(cupomOptional.isEmpty()) {
            log.warn("Cupom Inexistente: {}", chave);
            return null;
        }

        return cupomMapper.toDomain(cupomOptional.get());
    }

    @Override
    public Long buscarIdPorChave(String chaveCupom) {
        Long cupomId = cupomRepository.findIdByChaveIgnoreCase(chaveCupom);

        if (cupomId == null) {
            return null;
        }

        return cupomId;
    }
}
