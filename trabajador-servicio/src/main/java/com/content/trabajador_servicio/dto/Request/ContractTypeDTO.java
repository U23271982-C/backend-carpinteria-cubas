package com.content.trabajador_servicio.dto.Request;

import com.content.trabajador_servicio.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Data Transfer Object para la entidad ContractType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractTypeDTO {

    private Integer id;

    private String contract_Type;

    private String descripcion;

    private List<Contract> contracts;

    private Integer id_State;
}
