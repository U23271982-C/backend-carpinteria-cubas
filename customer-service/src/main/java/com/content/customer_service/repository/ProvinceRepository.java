package com.content.customer_service.repository;

import com.content.customer_service.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Provincias
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    // Buscar provincia por nombre y departamento (ignora mayúsculas/minúsculas)
    Optional<Province> findByProvince_nameIgnoreCaseAndDepartment_id_Department_id(String provinceName, Integer departmentId);

    // Buscar provincias por departamento
    List<Province> findByDepartment_id_Department_id(Integer departmentId);

    // Verificar si existe una provincia por nombre y departamento
    boolean existsByProvince_nameIgnoreCaseAndDepartment_id_Department_id(String provinceName, Integer departmentId);
}

