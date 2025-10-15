package com.content.customer_service.repository;

import com.content.customer_service.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    Optional<Province> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT p FROM Province p WHERE p.uuid = :uuid AND p.state_entity_id.is_active = true")
    Optional<Province> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT p FROM Province p WHERE p.department_id.uuid = :departmentUuid AND p.state_entity_id.is_active = true")
    List<Province> findByDepartmentUuid(@Param("departmentUuid") String departmentUuid);

    // Buscar provincia por nombre y departamento ID
    @Query("SELECT p FROM Province p WHERE LOWER(p.province_name) = LOWER(:name) AND p.department_id.department_id = :departmentId AND p.state_entity_id.is_active = true")
    Optional<Province> findByNameAndDepartmentId(@Param("name") String name, @Param("departmentId") Integer departmentId);
}
