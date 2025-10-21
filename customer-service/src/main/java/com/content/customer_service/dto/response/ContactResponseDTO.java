package com.content.customer_service.dto.response;

import lombok.*;

import java.util.UUID;

/**
 * DTO de respuesta para Contact - Solo expone UUIDs, nunca IDs internos
 */
@Data
public class ContactResponseDTO {

    private UUID contact_uuid;
    private UUID client_uuid;
    private String phone_number;
    private String email;
    private String state_entity_name;

}
