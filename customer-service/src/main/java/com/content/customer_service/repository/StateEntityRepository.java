package com.content.customer_service.repository;

import com.content.customer_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {

    Optional<StateEntity> findByUuid(UUID uuid);

}
