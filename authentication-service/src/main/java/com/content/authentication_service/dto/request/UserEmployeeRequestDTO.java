package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserEmployeeRequestDTO {

    @NotBlank
    @Size(min = 1, max = 150)
    private String full_name;

    @NotBlank
    @Size(min = 1, max = 100)
    private String user_name;

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
