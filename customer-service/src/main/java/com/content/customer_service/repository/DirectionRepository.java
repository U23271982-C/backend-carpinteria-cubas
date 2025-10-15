package com.content.customer_service.repository;

import com.content.customer_service.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    // Método principal: Buscar dirección por UUID
    Optional<Direction> findByUuid(String uuid);

    // Verificar existencia por UUID
    boolean existsByUuid(String uuid);

    // Buscar dirección activa por UUID
    @Query("SELECT d FROM Direction d WHERE d.uuid = :uuid AND d.state_entity_id.is_active = true")
    Optional<Direction> findActiveByUuid(@Param("uuid") String uuid);

    // Buscar todas las direcciones de un cliente por UUID del cliente
    @Query("SELECT d FROM Direction d WHERE d.client_id.uuid = :clientUuid AND d.state_entity_id.is_active = true")
    List<Direction> findByClientUuid(@Param("clientUuid") String clientUuid);

    // Buscar todas las direcciones activas
    @Query("SELECT d FROM Direction d WHERE d.state_entity_id.is_active = true")
    List<Direction> findAllActive();
}
