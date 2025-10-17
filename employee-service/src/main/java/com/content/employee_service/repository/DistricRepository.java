package com.content.employee_service.repository;

import com.content.employee_service.model.Distric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DistricRepository extends JpaRepository<Distric, Integer> {
    Optional<Distric> findByUuid(UUID uuid);
}
