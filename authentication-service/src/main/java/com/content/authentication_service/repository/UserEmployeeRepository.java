package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Integer> {
    Optional<UserEmployee>  findByUuid(UUID uuid);
    Optional<UserEmployee> findByUsername(String user_employee_name);
}
