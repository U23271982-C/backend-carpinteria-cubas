package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserAccessActionResponseDTO {

    private UUID uuid;
    private String employee_name;
    private String action_name;
    private String module_name;

}
