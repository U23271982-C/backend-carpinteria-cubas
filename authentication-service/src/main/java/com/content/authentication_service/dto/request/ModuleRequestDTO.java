package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ModuleRequestDTO {

    @NotBlank(message = "El nombre del módulo es obligatorio")
    private String module_name;

    @NotBlank(message = "La descripción del módulo es obligatoria")
    private String module_description;

    private UUID stateEntityuuid;
}
