package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SessionRequestDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
