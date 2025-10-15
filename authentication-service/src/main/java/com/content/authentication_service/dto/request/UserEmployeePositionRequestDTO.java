package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserEmployeePositionRequestDTO {

    @NotEmpty(message = "El nombre del puesto es obligatorio")
    private String positionName;

    @NotBlank(message = "La descripción del puesto es obligatoria")
    private String positionDescription;

    private UUID stateEntityuuid;
}
