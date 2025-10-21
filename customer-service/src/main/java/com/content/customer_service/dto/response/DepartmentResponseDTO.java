package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Departamento.
 */
@Data
public class DepartmentResponseDTO {

    private UUID department_uuid;
    private String department_name;
    private String state_entity_name;

}



