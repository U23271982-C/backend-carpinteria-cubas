package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserClientRepository extends JpaRepository<UserClient,Integer> {
    Optional<UserClient> findByUuid(UUID uuid);
    Optional<UserClient> findByFireBaseUid(String fireBaseUid);
}
