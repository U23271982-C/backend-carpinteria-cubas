package com.content.customer_service.repository;

import com.content.customer_service.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    Optional<District> findByUuid(UUID uuid);

}
