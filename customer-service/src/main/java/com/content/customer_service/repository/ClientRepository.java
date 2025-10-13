package com.content.customer_service.repository;

import com.content.customer_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Clientes
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Buscar cliente por documento de identificación usando query personalizada
    @Query("SELECT c FROM Client c WHERE c.identification_id.identification_doc = :identificationDoc")
    Optional<Client> findByIdentificationDoc(@Param("identificationDoc") String identificationDoc);

    // Verificar si existe un cliente con un documento de identificación usando query personalizada
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.identification_id.identification_doc = :identificationDoc")
    boolean existsByIdentificationDoc(@Param("identificationDoc") String identificationDoc);
}
