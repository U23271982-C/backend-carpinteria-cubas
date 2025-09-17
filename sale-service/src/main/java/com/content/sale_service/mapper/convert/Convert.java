package com.content.sale_service.mapper.convert;

/**
 *
 * @param <M> Modelo
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Convert<M, DRQ, DRE>
        extends ConvertModel<M, DRQ>, ConvertDTO<M, DRE> {

}
