package com.content.customer_service.repository;

import com.content.customer_service.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

    Optional<ClientType> findByUuid(UUID uuid);

}
