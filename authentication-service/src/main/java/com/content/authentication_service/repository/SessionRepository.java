package com.content.authentication_service.repository;

import com.content.authentication_service.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByUuid(UUID uuid);
}
