package com.postech.gerencie.pedidos.domain.mapper;

public interface Mapper {
    <D> D map(Object source, Class<D> destinationType);
    void map(Object source, Object destination);
}