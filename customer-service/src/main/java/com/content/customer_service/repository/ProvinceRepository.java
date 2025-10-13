package com.content.customer_service.repository;

import com.content.customer_service.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Provincias
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    // Buscar provincia por nombre y departamento (ignora mayúsculas/minúsculas) usando query personalizada
    @Query("SELECT p FROM Province p WHERE LOWER(p.province_name) = LOWER(:provinceName) AND p.department_id.department_id = :departmentId")
    Optional<Province> findByNameAndDepartmentId(@Param("provinceName") String provinceName, @Param("departmentId") Integer departmentId);

    // Buscar provincias por departamento usando query personalizada
    @Query("SELECT p FROM Province p WHERE p.department_id.department_id = :departmentId")
    List<Province> findByDepartmentId(@Param("departmentId") Integer departmentId);

    // Verificar si existe una provincia por nombre y departamento usando query personalizada
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Province p WHERE LOWER(p.province_name) = LOWER(:provinceName) AND p.department_id.department_id = :departmentId")
    boolean existsByNameAndDepartmentId(@Param("provinceName") String provinceName, @Param("departmentId") Integer departmentId);
}
