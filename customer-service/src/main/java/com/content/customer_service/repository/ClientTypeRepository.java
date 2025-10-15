package com.content.customer_service.repository;

import com.content.customer_service.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

    Optional<ClientType> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT ct FROM ClientType ct WHERE ct.uuid = :uuid AND ct.state_entity_id.is_active = true")
    Optional<ClientType> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT ct FROM ClientType ct WHERE ct.state_entity_id.is_active = true")
    List<ClientType> findAllActive();
}
