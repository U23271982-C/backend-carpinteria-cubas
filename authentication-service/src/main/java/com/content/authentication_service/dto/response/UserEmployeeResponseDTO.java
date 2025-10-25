package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserEmployeeResponseDTO {

    private UUID uuid;
    private String full_name;
    private String user_name;
    private String position;
    private String phone;
    private String state;

}
