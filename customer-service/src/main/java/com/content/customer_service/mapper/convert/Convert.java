package com.content.customer_service.mapper.convert;

/**
 * Interfaz genérica para conversión entre Modelo, Request DTO y Response DTO
 * @param <M> Modelo de la entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
public interface Convert<M, DRQ, DRE> {

    /**
     * Convierte un modelo a DTO de respuesta
     * @param modelo Entidad del modelo
     * @return DTO de respuesta
     */
    DRE toDTO(M modelo);

    /**
     * Convierte un DTO de request a modelo
     * @param dto DTO de entrada
     * @return Entidad del modelo
     */
    M toModel(DRQ dto);
}

