package com.content.customer_service.mapper.convert;

/**
 * Interfaz genérica para conversión entre Modelo, Request DTO y Response DTO
 * @param <M> Modelo de la entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
public interface Convert<M, DRQ, DRE> extends ConvertModel<M, DRQ>, ConvertDTO<M, DRE> {

}

