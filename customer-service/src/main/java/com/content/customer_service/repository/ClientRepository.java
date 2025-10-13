package com.content.customer_service.repository;

import com.content.customer_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Clientes
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Buscar cliente por documento de identificación
    Optional<Client> findByIdentification_id_IdentificationDoc(String identificationDoc);

    // Verificar si existe un cliente con un documento de identificación
    boolean existsByIdentification_id_IdentificationDoc(String identificationDoc);
}

