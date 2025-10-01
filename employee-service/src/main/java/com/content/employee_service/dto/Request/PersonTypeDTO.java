package com.content.employee_service.dto.Request;

import com.content.employee_service.model.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Data Transfer Object para la entidad PersonType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonTypeDTO {

    private Integer id;

    private String person_Type;

    private List<IdentificationType> identificationTypes;

    private Integer id_State;
}
