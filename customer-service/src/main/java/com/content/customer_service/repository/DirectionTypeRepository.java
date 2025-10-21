package com.content.customer_service.repository;

import com.content.customer_service.model.DirectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DirectionTypeRepository extends JpaRepository<DirectionType, Integer> {

    Optional<DirectionType> findByUuid(UUID uuid);

}
