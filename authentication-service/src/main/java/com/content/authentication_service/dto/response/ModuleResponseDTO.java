package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ModuleResponseDTO {

    private UUID uuid;

    private String module_name;

    private String module_description;

    private String state_entity_name;
}
