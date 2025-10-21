package com.content.customer_service.repository;

import com.content.customer_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<Contact> findByUuid(UUID uuid);

}
