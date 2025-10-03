package com.content.inventory_matter_service.service.abstractService;

public interface ServiceAbs<DRQ, DRE>
        extends
        Creatable<DRQ, DRE>,
        Readable<DRE>,
        Updatable<DRQ,DRE>,
        Removable, Listable<DRE> {
}
