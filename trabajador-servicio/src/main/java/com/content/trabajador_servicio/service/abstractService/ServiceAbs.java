package com.content.trabajador_servicio.service.abstractService;

public interface ServiceAbs<DRE,DRQ>
        extends Cretable<DRE>,
        Readable<DRE>,
        Updatable<DRE>,
        Removable,
        Listable<DRE>{
}
