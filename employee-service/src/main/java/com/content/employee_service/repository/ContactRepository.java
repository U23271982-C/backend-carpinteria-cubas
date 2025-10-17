package com.content.employee_service.repository;

import com.content.employee_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByUuid(UUID uuid);
}
