package com.content.trabajador_servicio.dto.Request;

import com.content.trabajador_servicio.model.Identification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Data Transfer Object para la entidad IdentificationType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationTypeDTO {

    private Integer id;

    private String identification_type;

    private Integer id_Person_Type;

    private List<Identification> identifications;

    private Integer id_State;
}
