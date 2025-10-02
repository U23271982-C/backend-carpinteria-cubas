package com.content.inventario_stock_servicio.mapper.convert;

/**
 *
 * @param <M> modelo
 * @param <DRQ> Request DTO (Datos que ingresan al sistema)
 * @param <DRE> Response DTO (Datos que egresan del sistema)
 */
public interface Convert <M,DRQ,DRE> extends ConvertModel<M,DRQ>, ConvertDTO<M,DRE>{

}
