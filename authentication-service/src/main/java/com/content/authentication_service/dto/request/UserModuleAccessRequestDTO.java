package com.content.authentication_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserModuleAccessRequestDTO {

    private UUID employee_uuid;
    private UUID module_uuid;

}
