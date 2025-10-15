package com.content.customer_service.repository;

import com.content.customer_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Query("SELECT d FROM Department d WHERE d.uuid = :uuid AND d.state_entity_id.is_active = true")
    Optional<Department> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT d FROM Department d WHERE d.state_entity_id.is_active = true")
    List<Department> findAllActive();

    // Buscar departamento por nombre (ignorando mayúsculas/minúsculas)
    @Query("SELECT d FROM Department d WHERE LOWER(d.department_name) = LOWER(:name) AND d.state_entity_id.is_active = true")
    Optional<Department> findByNameIgnoreCase(@Param("name") String name);
}
