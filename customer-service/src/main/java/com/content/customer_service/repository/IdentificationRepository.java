package com.content.customer_service.repository;

import com.content.customer_service.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Identificaciones
 */
@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Integer> {

    // Buscar identificación por documento
    Optional<Identification> findByIdentification_doc(String identificationDoc);

    // Verificar si existe una identificación por documento
    boolean existsByIdentification_doc(String identificationDoc);
}

