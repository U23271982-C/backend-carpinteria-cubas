package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Contacto.
 */
@Getter
@Setter
@Builder
public class ContactResponseDTO {

    private Integer contact_id;
    private Integer client_id;
    private String client_name;
    private String phone_number;
    private String email;
    private Integer state_entity_id;
    private String state_entity_name;

}



