package com.content.authentication_service.repository;

import com.content.authentication_service.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action,Integer> {
}
