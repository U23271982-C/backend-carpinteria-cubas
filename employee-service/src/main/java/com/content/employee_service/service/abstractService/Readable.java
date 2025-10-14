package com.content.employee_service.service.abstractService;


import java.util.UUID;

public interface Readable<DRE>{
    DRE readByUUID(UUID uuid);
}
