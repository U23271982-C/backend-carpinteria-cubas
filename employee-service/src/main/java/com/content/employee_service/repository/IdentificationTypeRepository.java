package com.content.employee_service.repository;

import com.content.employee_service.model.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {
    Optional<IdentificationType> findByUuid(UUID uuid);
}
