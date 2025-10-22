package com.content.customer_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Interfaz para listar todas las entidades
 * @param <DRE> DTO de Response (salida)
 */
@Transactional
public interface Listable<DRE> {
    List<DRE> allList();
}

