package com.content.employee_service.repository;

import com.content.employee_service.model.Contract;
import com.content.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUuid(UUID uuid);
}
