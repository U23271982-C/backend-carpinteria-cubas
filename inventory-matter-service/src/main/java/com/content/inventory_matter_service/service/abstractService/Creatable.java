package com.content.inventory_matter_service.service.abstractService;

public interface Creatable <DRQ, DRE>{
    DRE create(DRQ dto);
}
