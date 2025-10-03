package com.content.inventory_matter_service.service.abstractService;

public interface Updatable <DRQ,DRE> {
    DRE update(int id, DRQ dto);
}
