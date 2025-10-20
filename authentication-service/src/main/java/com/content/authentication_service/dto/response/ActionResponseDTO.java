package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ActionResponseDTO {

    private UUID uuid;

    private String action_name;

    private String action_description;
}
