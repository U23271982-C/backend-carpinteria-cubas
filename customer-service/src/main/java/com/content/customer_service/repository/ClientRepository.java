package com.content.customer_service.repository;

import com.content.customer_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByUuid(UUID uuid);

}
