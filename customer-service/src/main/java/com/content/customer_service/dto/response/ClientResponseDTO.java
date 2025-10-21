package com.content.customer_service.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO de respuesta para Cliente - Solo expone UUIDs, nunca IDs internos
 */
@Data
public class ClientResponseDTO {

    private UUID client_uuid;
    private String client_name;
    private String client_last_name;
    private LocalDateTime registration_date;
    private UUID client_type_uuid;
    private UUID identification_uuid;
    private String state_entity_name;

    // Listas de contactos y direcciones (solo UUIDs)
    private List<ContactSummaryDTO> contacts;
    private List<DirectionSummaryDTO> directions;

    @Data
    public static class ContactSummaryDTO {
        private String phoneNumber;
        private String email;
        private String state_entity_name;
    }

    @Data
    public static class DirectionSummaryDTO {
        private String direction_type_name;
        private String direction_name;
        private String direction_number;
        private String reference;
        private String district_name;
        private String state_entity_name;
    }
}
