package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequestDTO {
    @NotEmpty
    private String password;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String confirmPassword;
}
