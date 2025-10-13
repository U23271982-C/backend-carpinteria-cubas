
package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Departamento.
 */
@Getter
@Setter
@Builder
public class DepartmentResponseDTO {
    private Integer department_id;
    private String department_name;
    private Integer state_entity_id;
    private String state_entity_name;

}



