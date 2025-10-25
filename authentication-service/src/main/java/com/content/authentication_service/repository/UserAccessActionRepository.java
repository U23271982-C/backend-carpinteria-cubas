package com.content.authentication_service.repository;

import com.content.authentication_service.model.Action;
import com.content.authentication_service.model.UserAccessAction;
import com.content.authentication_service.model.UserModuleAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserAccessActionRepository extends JpaRepository<UserAccessAction, Integer> {
    Optional<UserAccessAction> findByUuid(UUID uuid);
    Optional<UserAccessAction> findByUmaIdAndActionId(UserModuleAccess uma, Action action);
}
