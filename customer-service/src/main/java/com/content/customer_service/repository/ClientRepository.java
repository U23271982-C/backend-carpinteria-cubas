package com.content.customer_service.repository;

import com.content.customer_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Método principal: Buscar cliente por UUID (identificador público)
    Optional<Client> findByUuid(String uuid);

    // Verificar si existe un cliente con un UUID específico
    boolean existsByUuid(String uuid);

    // Buscar clientes activos por UUID
    @Query("SELECT c FROM Client c WHERE c.uuid = :uuid AND c.state_entity_id.is_active = true")
    Optional<Client> findActiveByUuid(@Param("uuid") String uuid);

    // Buscar todos los clientes activos
    @Query("SELECT c FROM Client c WHERE c.state_entity_id.is_active = true")
    List<Client> findAllActive();

    // Buscar clientes por nombre (para búsquedas)
    @Query("SELECT c FROM Client c WHERE " +
           "(LOWER(c.client_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.client_last_name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
           "c.state_entity_id.is_active = true")
    List<Client> findByNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    // Buscar cliente por número de identificación
    @Query("SELECT c FROM Client c WHERE c.identification_id.identification_number = :identificationNumber AND c.state_entity_id.is_active = true")
    Optional<Client> findByIdentificationNumber(@Param("identificationNumber") String identificationNumber);
}
