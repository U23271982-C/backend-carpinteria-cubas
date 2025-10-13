package com.content.customer_service.repository;

import com.content.customer_service.model.DirectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Dirección
 */
@Repository
public interface DirectionTypeRepository extends JpaRepository<DirectionType, Integer> {

    // Buscar tipo de dirección por descripción
    Optional<DirectionType> findByDescription(String description);
}

