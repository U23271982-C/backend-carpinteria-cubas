package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserModuleAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModuleAccessRepository extends JpaRepository<UserModuleAccess, Integer> {
}
