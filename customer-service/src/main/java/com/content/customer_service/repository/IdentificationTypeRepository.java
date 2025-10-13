package com.content.customer_service.repository;

import com.content.customer_service.model.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Identificación
 */
@Repository
public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {

    // Buscar tipo de identificación por nombre
    Optional<IdentificationType> findByIdentification_type_name(String identificationTypeName);

    // Buscar tipos de identificación por tipo de persona
    List<IdentificationType> findByPerson_type_id_Person_type_id(Integer personTypeId);
}

