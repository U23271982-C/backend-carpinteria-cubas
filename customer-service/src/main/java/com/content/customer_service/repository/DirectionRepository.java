package com.content.customer_service.repository;

import com.content.customer_service.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    Optional<Direction> findByUuid(UUID uuid);

}
