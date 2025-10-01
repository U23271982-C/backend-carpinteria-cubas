package com.content.employee_service.service.abstractService;

public interface Readable<DRE>{
    DRE readById(Long id);
}
