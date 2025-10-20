package com.content.authentication_service.repository;

import com.content.authentication_service.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}
