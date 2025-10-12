package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClientRepository extends JpaRepository<UserClient,Integer> {
    Optional<UserClient> findByFireBaseUid(String fireBaseUid);
}
