package com.content.customer_service.repository;

import com.content.customer_service.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de Distritos
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    // Buscar distrito por nombre y provincia (ignora mayúsculas/minúsculas)
    Optional<District> findByDistrict_nameIgnoreCaseAndProvince_id_Province_id(String districtName, Integer provinceId);

    // Buscar distritos por provincia
    List<District> findByProvince_id_Province_id(Integer provinceId);

    // Verificar si existe un distrito por nombre y provincia
    boolean existsByDistrict_nameIgnoreCaseAndProvince_id_Province_id(String districtName, Integer provinceId);
}

