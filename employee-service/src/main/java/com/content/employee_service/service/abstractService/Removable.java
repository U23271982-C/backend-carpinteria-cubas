package com.content.employee_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.UUID;
@Transactional
public interface Removable{
    void remove(UUID uuid);
}
