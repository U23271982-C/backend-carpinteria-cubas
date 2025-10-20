package com.content.authentication_service.repository;

import com.content.authentication_service.model.UserAccessAction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccessActionRepository extends JpaRepository<UserAccessAction, Integer> {
}
