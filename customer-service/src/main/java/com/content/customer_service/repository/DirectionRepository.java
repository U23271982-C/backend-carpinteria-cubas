package com.content.customer_service.repository;

import com.content.customer_service.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestión de Direcciones
 */
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    // Buscar direcciones por cliente
    List<Direction> findByClient_id_Client_id(Integer clientId);

    // Buscar direcciones por distrito
    List<Direction> findByDistrict_id_District_id(Integer districtId);
}

