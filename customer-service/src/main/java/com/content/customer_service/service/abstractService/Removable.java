package com.content.customer_service.service.abstractService;

/**
 * Interfaz para eliminar una entidad
 */
public interface Removable {
    /**
     * Elimina una entidad por su ID
     * @param id ID de la entidad a eliminar
     */
    void remove(int id);
}

