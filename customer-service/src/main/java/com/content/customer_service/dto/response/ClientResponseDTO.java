package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para Cliente.
 * Contiene toda la información del cliente incluyendo datos generados automáticamente.
 */
@Getter
@Setter
@Builder
public class ClientResponseDTO {

    private Integer client_id;
    private String client_name;
    private String client_last_name;
    private LocalDateTime registration_date;
    private Integer client_type_id;
    private String client_type_name;
    private Integer identification_id;
    private String identification_doc;
    private Integer state_entity_id;
    private String state_entity_name;

}

