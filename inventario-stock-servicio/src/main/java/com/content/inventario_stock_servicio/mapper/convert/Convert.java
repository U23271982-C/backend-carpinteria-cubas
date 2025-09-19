package com.content.inventario_stock_servicio.mapper.convert;

/**
 *
 * @param <M> modelo
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Convert <M,DRQ,DRE> extends ConvertModel<M,DRQ>, ConvertDTO<M,DRE>{

}
