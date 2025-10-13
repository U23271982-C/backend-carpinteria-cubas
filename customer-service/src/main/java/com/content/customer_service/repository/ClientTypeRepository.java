package com.content.customer_service.repository;

import com.content.customer_service.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Cliente
 */
@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

    // Buscar tipo de cliente por nombre usando query personalizada
    @Query("SELECT ct FROM ClientType ct WHERE ct.client_type_name = :clientTypeName")
    Optional<ClientType> findByName(@Param("clientTypeName") String clientTypeName);
}
