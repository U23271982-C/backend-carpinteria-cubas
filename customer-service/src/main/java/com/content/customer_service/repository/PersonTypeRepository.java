package com.content.customer_service.repository;

import com.content.customer_service.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {

    Optional<PersonType> findByUuid(UUID uuid);

}
