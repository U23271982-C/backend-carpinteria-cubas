package com.content.inventario_stock_servicio.service.abstractService;

public interface ServiceAbs<DRQ, DRE>
        extends
        Creatable<DRQ, DRE>,
        Readable<DRE>,
        Updatable<DRQ,DRE>,
        Removable, Listable<DRE> {
}
