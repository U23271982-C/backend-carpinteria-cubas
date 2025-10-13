package com.content.customer_service.repository;

import com.content.customer_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestión de Departamentos
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Buscar departamento por nombre (ignora mayúsculas/minúsculas) usando query personalizada
    @Query("SELECT d FROM Department d WHERE LOWER(d.department_name) = LOWER(:departmentName)")
    Optional<Department> findByNameIgnoreCase(@Param("departmentName") String departmentName);

    // Verificar si existe un departamento por nombre usando query personalizada
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE LOWER(d.department_name) = LOWER(:departmentName)")
    boolean existsByNameIgnoreCase(@Param("departmentName") String departmentName);
}
