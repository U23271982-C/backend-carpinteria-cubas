package com.content.customer_service.repository;

import com.content.customer_service.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {

    Optional<PersonType> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT pt FROM PersonType pt WHERE pt.uuid = :uuid AND pt.state_entity_id.is_active = true")
    Optional<PersonType> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT pt FROM PersonType pt WHERE pt.state_entity_id.is_active = true")
    List<PersonType> findAllActive();
}
