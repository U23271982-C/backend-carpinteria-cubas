package com.content.customer_service.service.abstractService;

/**
 * Agrupación de métodos que se deben implementar en el servicio del modelo
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
public interface ServiceAbs<DRQ, DRE>
        extends Creatable<DRQ, DRE>,
        Readable<DRE>,
        Updatable<DRQ, DRE>,
        Removable,
        Listable<DRE> {
}

