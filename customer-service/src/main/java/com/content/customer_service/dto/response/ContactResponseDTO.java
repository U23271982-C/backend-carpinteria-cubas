package com.content.customer_service.dto.response;

import lombok.*;

/**
 * DTO de respuesta para Contact - Solo expone UUIDs, nunca IDs internos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {

    private String uuid; // UUID público
    private String phoneNumber;
    private String email;

    // Referencias usando UUIDs
    private String clientUuid;
    private String clientName;

    private String stateEntityUuid;
    private String stateName;

    // Métodos de compatibilidad para controladores legacy
    public String getContact_id() {
        return uuid;
    }
}
