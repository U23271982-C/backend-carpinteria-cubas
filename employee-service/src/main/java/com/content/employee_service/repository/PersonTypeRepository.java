package com.content.employee_service.repository;

import com.content.employee_service.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {
    Optional<PersonType> findByUuid(UUID uuid);
}
