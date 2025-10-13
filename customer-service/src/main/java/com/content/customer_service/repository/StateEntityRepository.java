package com.content.customer_service.repository;

import com.content.customer_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestión de Estados de Entidades
 */
@Repository
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {
    // Buscar estado de entidad por nombre usando query personalizada
    @Query("SELECT se FROM StateEntity se WHERE se.state_entity_name = :stateEntityName")
    StateEntity findByName(@Param("stateEntityName") String stateEntityName);
}