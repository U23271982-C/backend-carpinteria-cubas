package com.content.authentication_service.repository;

import com.content.authentication_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {
    Optional<StateEntity> findByUuid(UUID uuid);
    Optional<StateEntity> findByStateId(Integer state_Entity_id);
}
