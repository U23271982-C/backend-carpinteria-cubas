package com.content.customer_service.repository;

import com.content.customer_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Método principal: Buscar contacto por UUID
    Optional<Contact> findByUuid(String uuid);

    // Verificar existencia por UUID
    boolean existsByUuid(String uuid);

    // Buscar contacto activo por UUID
    @Query("SELECT c FROM Contact c WHERE c.uuid = :uuid AND c.state_entity_id.is_active = true")
    Optional<Contact> findActiveByUuid(@Param("uuid") String uuid);

    // Buscar todos los contactos de un cliente por UUID del cliente
    @Query("SELECT c FROM Contact c WHERE c.client_id.uuid = :clientUuid AND c.state_entity_id.is_active = true")
    List<Contact> findByClientUuid(@Param("clientUuid") String clientUuid);

    // Buscar contacto por email
    @Query("SELECT c FROM Contact c WHERE c.email = :email AND c.state_entity_id.is_active = true")
    Optional<Contact> findByEmail(@Param("email") String email);

    // Verificar si existe un contacto con el email dado
    @Query("SELECT COUNT(c) > 0 FROM Contact c WHERE c.email = :email AND c.state_entity_id.is_active = true")
    boolean existsByEmail(@Param("email") String email);

    // Buscar contacto por teléfono
    @Query("SELECT c FROM Contact c WHERE c.phone_number = :phoneNumber AND c.state_entity_id.is_active = true")
    Optional<Contact> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
