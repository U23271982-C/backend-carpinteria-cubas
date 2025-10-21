package com.content.customer_service.repository;

import com.content.customer_service.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Integer> {

    Optional<Identification> findByUuid(UUID uuid);

}
