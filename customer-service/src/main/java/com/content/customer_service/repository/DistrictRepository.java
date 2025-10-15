package com.content.customer_service.repository;

import com.content.customer_service.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    Optional<District> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT d FROM District d WHERE d.uuid = :uuid AND d.state_entity_id.is_active = true")
    Optional<District> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT d FROM District d WHERE d.province_id.uuid = :provinceUuid AND d.state_entity_id.is_active = true")
    List<District> findByProvinceUuid(@Param("provinceUuid") String provinceUuid);

    // Buscar distrito por nombre y provincia ID
    @Query("SELECT d FROM District d WHERE LOWER(d.district_name) = LOWER(:name) AND d.province_id.province_id = :provinceId AND d.state_entity_id.is_active = true")
    Optional<District> findByNameAndProvinceId(@Param("name") String name, @Param("provinceId") Integer provinceId);
}
