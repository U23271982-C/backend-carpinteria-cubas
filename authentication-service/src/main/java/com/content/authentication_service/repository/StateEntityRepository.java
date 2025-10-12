package com.content.authentication_service.repository;

import com.content.authentication_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateEntityRepository extends JpaRepository<StateEntity,Integer> {
}
