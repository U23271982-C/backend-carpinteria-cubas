package com.content.customer_service.service.abstractService;

import java.util.List;

/**
 * Interfaz para listar todas las entidades
 * @param <DRE> DTO de Response (salida)
 */
public interface Listable<DRE> {
    /**
     * Obtiene todas las entidades
     * @return Lista de DTOs de respuesta
     */
    List<DRE> allList();
}

