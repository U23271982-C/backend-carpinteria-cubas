package com.content.customer_service.repository;

import com.content.customer_service.model.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Identificación
 */
@Repository
public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {

    // Buscar tipo de identificación por nombre usando query personalizada
    @Query("SELECT it FROM IdentificationType it WHERE it.identification_type_name = :identificationTypeName")
    Optional<IdentificationType> findByName(@Param("identificationTypeName") String identificationTypeName);

    // Buscar tipos de identificación por tipo de persona usando query personalizada
    @Query("SELECT it FROM IdentificationType it WHERE it.person_type_id.person_type_id = :personTypeId")
    List<IdentificationType> findByPersonTypeId(@Param("personTypeId") Integer personTypeId);
}
