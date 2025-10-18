package com.content.inventory_matter_service.mapper.convert;

/**
 *
 * @param <M> modelo
 * @param <DRQ> request DTO (Datos que ingresan al sistema)
 * @param <DRE> response DTO (Datos que egresan del sistema)
 */
public interface Convert <M,DRQ,DRE> extends ConvertModel<M,DRQ>, ConvertDTO<M,DRE>{

}
