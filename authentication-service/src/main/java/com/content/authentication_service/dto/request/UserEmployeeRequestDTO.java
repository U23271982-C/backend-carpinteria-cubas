package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
@Getter
@Setter
public class UserEmployeeRequestDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank
    private UUID positionUUID;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phone;

    private UUID stateUUID;
}
