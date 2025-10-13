package com.content.customer_service.repository;

import com.content.customer_service.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Identificaciones
 */
@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Integer> {

    // Buscar identificación por documento usando query personalizada
    @Query("SELECT i FROM Identification i WHERE i.identification_doc = :identificationDoc")
    Optional<Identification> findByDoc(@Param("identificationDoc") String identificationDoc);

    // Verificar si existe una identificación por documento usando query personalizada
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Identification i WHERE i.identification_doc = :identificationDoc")
    boolean existsByDoc(@Param("identificationDoc") String identificationDoc);
}
