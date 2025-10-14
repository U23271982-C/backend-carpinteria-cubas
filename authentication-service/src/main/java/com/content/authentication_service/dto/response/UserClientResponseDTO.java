package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserClientResponseDTO {
    private String fire_base_uid;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private Integer state; // Devolvemos solo el nombre del estado para simplicidad.
    private LocalDate createdAt;
}
