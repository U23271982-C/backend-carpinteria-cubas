package com.content.customer_service.repository;

import com.content.customer_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Departamentos
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Buscar departamento por nombre (ignora mayúsculas/minúsculas)
    Optional<Department> findByDepartment_nameIgnoreCase(String departmentName);

    // Verificar si existe un departamento por nombre
    boolean existsByDepartment_nameIgnoreCase(String departmentName);
}

