package com.content.customer_service.repository;

import com.content.customer_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestión de Estados de Entidades
 */
@Repository
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {
    // Buscar estado de entidad por nombre
    StateEntity findByState_entity_name(String stateEntityName);
}