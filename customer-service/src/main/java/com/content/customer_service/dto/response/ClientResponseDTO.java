package com.content.customer_service.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de respuesta para Cliente - Solo expone UUIDs, nunca IDs internos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private String uuid; // UUID público - NUNCA exponemos el ID interno
    private String clientName;
    private String clientLastName;
    private LocalDateTime registrationDate;

    // Referencias usando UUIDs, no IDs internos
    private String clientTypeUuid;
    private String clientTypeName;

    private String identificationUuid;
    private String identificationNumber;
    private String identificationTypeName;

    private String stateEntityUuid;
    private String stateName;

    // Listas de contactos y direcciones (solo UUIDs)
    private List<ContactSummaryDTO> contacts;
    private List<DirectionSummaryDTO> directions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactSummaryDTO {
        private String uuid;
        private String phoneNumber;
        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DirectionSummaryDTO {
        private String uuid;
        private String directionName;
        private String addressLine1;
        private String districtName;
        private String provinceName;
    }
}
