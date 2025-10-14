package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserClientResponseDTO {

    private String uuid;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private LocalDate createdAt;
}
