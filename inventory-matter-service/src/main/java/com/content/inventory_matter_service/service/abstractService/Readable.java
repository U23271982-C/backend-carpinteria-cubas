package com.content.inventory_matter_service.service.abstractService;

public interface Readable <DRE> {
    DRE readById(Long id);
}
