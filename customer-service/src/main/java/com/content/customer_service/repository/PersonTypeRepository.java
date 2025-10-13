package com.content.customer_service.repository;

import com.content.customer_service.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Persona
 */
@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {

    // Buscar tipo de persona por nombre usando query personalizada
    @Query("SELECT pt FROM PersonType pt WHERE pt.persona_type_name = :personaTypeName")
    Optional<PersonType> findByName(@Param("personaTypeName") String personaTypeName);
}
