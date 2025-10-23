package com.content.employee_service.repository;

import com.content.employee_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Solamente sirve para la consulta de los estados.
 */
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {
}
