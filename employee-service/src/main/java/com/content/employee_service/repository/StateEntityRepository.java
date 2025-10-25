package com.content.employee_service.repository;

import com.content.employee_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Solamente sirve para la consulta de los estados.
 */
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {
    Optional<StateEntity> findByUuid(UUID uuid);
}
