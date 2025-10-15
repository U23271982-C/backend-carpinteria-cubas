package com.content.customer_service.repository;

import com.content.customer_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateEntityRepository extends JpaRepository<StateEntity, Integer> {

    // Método principal: Buscar por UUID
    Optional<StateEntity> findByUuid(String uuid);

    // Verificar existencia por UUID
    boolean existsByUuid(String uuid);

    // Buscar estados activos
    @Query("SELECT s FROM StateEntity s WHERE s.is_active = true")
    List<StateEntity> findAllActive();

    // Buscar por nombre de estado
    @Query("SELECT s FROM StateEntity s WHERE LOWER(s.state_name) = LOWER(:stateName)")
    Optional<StateEntity> findByStateName(@Param("stateName") String stateName);
}
