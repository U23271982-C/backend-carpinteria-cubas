package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
