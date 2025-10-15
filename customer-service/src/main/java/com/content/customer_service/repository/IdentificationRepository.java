package com.content.customer_service.repository;

import com.content.customer_service.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Integer> {

    // Método principal: Buscar por UUID
    Optional<Identification> findByUuid(String uuid);

    // Verificar existencia por UUID
    boolean existsByUuid(String uuid);

    // Buscar identificación activa por UUID
    @Query("SELECT i FROM Identification i WHERE i.uuid = :uuid AND i.state_entity_id.is_active = true")
    Optional<Identification> findActiveByUuid(@Param("uuid") String uuid);

    // Buscar por número de identificación
    @Query("SELECT i FROM Identification i WHERE i.identification_number = :identificationNumber AND i.state_entity_id.is_active = true")
    Optional<Identification> findByIdentificationNumber(@Param("identificationNumber") String identificationNumber);

    // Buscar por tipo de identificación
    @Query("SELECT i FROM Identification i WHERE i.identification_type_id.uuid = :typeUuid AND i.state_entity_id.is_active = true")
    List<Identification> findByTypeUuid(@Param("typeUuid") String typeUuid);
}
