package com.content.sale_service.mapper.convert;

/**
 *Conversion de entidades a DTO response y DTO request, viceversa
 * @param <M> Modelo
 * @param <DRQ> request DTO
 * @param <DRE> response DTO
 */
public interface Convert<M, DRQ, DRE>
        extends ConvertModel<M, DRQ>, ConvertDTO<M, DRE> {

}
