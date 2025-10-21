package com.content.customer_service.repository;

import com.content.customer_service.model.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {

    Optional<IdentificationType> findByUuid(UUID uuid);

}
