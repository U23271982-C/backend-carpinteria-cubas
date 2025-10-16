package com.content.employee_service.repository;

import com.content.employee_service.model.Contract;
import com.content.employee_service.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContractTypeRepository extends JpaRepository<ContractType, Integer> {
    Optional<ContractType> findByUuid(UUID uuid);
}
