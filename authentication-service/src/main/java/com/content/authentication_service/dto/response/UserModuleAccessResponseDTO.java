package com.content.authentication_service.dto.response;

import com.content.authentication_service.mapper.UserModuleAccessMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class UserModuleAccessResponseDTO {

    private UUID uuid;
    private String employee_name;
    private String module_name;
    private List<UserAccessActionResponseDTO> access_permissions;
}
