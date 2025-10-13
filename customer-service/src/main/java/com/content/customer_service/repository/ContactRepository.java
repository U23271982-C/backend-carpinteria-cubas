package com.content.customer_service.repository;

import com.content.customer_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestión de Contactos
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Buscar contactos por cliente usando query personalizada
    @Query("SELECT c FROM Contact c WHERE c.client_id.client_id = :clientId")
    List<Contact> findByClientId(@Param("clientId") Integer clientId);

    // Buscar contacto por email
    boolean existsByEmail(String email);
}
