package com.content.customer_service.repository;

import com.content.customer_service.model.DirectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionTypeRepository extends JpaRepository<DirectionType, Integer> {

    Optional<DirectionType> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT dt FROM DirectionType dt WHERE dt.uuid = :uuid AND dt.state_entity_id.is_active = true")
    Optional<DirectionType> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT dt FROM DirectionType dt WHERE dt.state_entity_id.is_active = true")
    List<DirectionType> findAllActive();
}
