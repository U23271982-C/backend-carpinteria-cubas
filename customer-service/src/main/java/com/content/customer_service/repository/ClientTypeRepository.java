package com.content.customer_service.repository;

import com.content.customer_service.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Cliente
 */
@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

    // Buscar tipo de cliente por nombre
    Optional<ClientType> findByClient_type_name(String clientTypeName);
}

