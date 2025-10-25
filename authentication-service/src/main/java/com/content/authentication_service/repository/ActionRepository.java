package com.content.authentication_service.repository;

import com.content.authentication_service.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActionRepository extends JpaRepository<Action,Integer> {
   Optional<Action> findByUuid(UUID uuid);
}
