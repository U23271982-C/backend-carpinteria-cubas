package com.content.employee_service.repository;

import com.content.employee_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByUuid(UUID uuid);
}
