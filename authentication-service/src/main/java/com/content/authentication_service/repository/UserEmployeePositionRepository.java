package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserEmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEmployeePositionRepository extends JpaRepository<UserEmployeePosition, Integer> {
    Optional<UserEmployeePosition> findByUuid(UUID uuid);
}
