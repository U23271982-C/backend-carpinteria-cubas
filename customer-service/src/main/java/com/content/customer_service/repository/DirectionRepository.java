package com.content.customer_service.repository;

import com.content.customer_service.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestión de Direcciones
 */
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    // Buscar direcciones por cliente usando query personalizada
    @Query("SELECT d FROM Direction d WHERE d.client_id.client_id = :clientId")
    List<Direction> findByClientId(@Param("clientId") Integer clientId);

    // Buscar direcciones por distrito usando query personalizada
    @Query("SELECT d FROM Direction d WHERE d.district_id.district_id = :districtId")
    List<Direction> findByDistrictId(@Param("districtId") Integer districtId);
}
