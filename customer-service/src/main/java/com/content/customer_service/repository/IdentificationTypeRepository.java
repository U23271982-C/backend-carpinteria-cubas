package com.content.customer_service.repository;

import com.content.customer_service.model.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {

    Optional<IdentificationType> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT it FROM IdentificationType it WHERE it.uuid = :uuid AND it.state_entity_id.is_active = true")
    Optional<IdentificationType> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT it FROM IdentificationType it WHERE it.state_entity_id.is_active = true")
    List<IdentificationType> findAllActive();
}
