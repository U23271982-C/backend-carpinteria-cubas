package com.content.customer_service.repository;

import com.content.customer_service.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    Optional<Province> findByUuid(UUID uuid);

}
