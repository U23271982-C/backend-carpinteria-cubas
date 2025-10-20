package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserEmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmployeePositionRepository extends JpaRepository<UserEmployeePosition, Integer> {
}
