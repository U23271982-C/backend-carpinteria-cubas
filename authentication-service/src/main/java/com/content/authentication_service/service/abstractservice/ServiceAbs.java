package com.content.authentication_service.service.abstractservice;

/**
 * Agrupación de métodos que se deben implementar en el servicio del modelo
 * @param <DRE> Response DTO
 */

public interface ServiceAbs<DRQ,DRE>
        extends Creatable<DRQ,DRE>,
        Readable<DRE>,
        Updatable<DRQ,DRE>,
        Removable,
        Listable<DRE>{
}
