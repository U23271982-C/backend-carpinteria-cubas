package com.content.customer_service.repository;

import com.content.customer_service.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Distritos
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    // Buscar distrito por nombre y provincia (ignora mayúsculas/minúsculas) usando query personalizada
    @Query("SELECT d FROM District d WHERE LOWER(d.district_name) = LOWER(:districtName) AND d.province_id.province_id = :provinceId")
    Optional<District> findByNameAndProvinceId(@Param("districtName") String districtName, @Param("provinceId") Integer provinceId);

    // Buscar distritos por provincia usando query personalizada
    @Query("SELECT d FROM District d WHERE d.province_id.province_id = :provinceId")
    List<District> findByProvinceId(@Param("provinceId") Integer provinceId);

    // Verificar si existe un distrito por nombre y provincia usando query personalizada
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM District d WHERE LOWER(d.district_name) = LOWER(:districtName) AND d.province_id.province_id = :provinceId")
    boolean existsByNameAndProvinceId(@Param("districtName") String districtName, @Param("provinceId") Integer provinceId);
}
