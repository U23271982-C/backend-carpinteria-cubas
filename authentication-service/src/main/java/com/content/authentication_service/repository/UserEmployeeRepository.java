package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserEmployee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Integer> {
}
