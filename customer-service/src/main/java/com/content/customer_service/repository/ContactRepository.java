package com.content.customer_service.repository;

import com.content.customer_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestión de Contactos
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Buscar contactos por cliente
    List<Contact> findByClient_id_Client_id(Integer clientId);

    // Buscar contacto por email
    boolean existsByEmail(String email);
}

