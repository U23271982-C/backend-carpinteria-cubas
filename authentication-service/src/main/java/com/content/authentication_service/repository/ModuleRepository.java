package com.content.authentication_service.repository;

import com.content.authentication_service.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    Optional<Module> findByUuid(UUID uuid);
    Optional<Module> findByName(String module_name);
}
