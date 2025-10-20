package com.content.authentication_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserAccessActionRequestDTO {

    private UUID action_uuid;
    private UUID user_module_access_uuid;

}
