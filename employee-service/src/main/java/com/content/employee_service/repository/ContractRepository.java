package com.content.employee_service.repository;

import com.content.employee_service.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Optional<Contract> findByUuid(UUID uuid);
}
