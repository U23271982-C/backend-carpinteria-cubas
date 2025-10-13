package com.content.customer_service.repository;

import com.content.customer_service.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Tipos de Persona
 */
@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {

    // Buscar tipo de persona por nombre
    Optional<PersonType> findByPersona_type_name(String personaTypeName);
}

