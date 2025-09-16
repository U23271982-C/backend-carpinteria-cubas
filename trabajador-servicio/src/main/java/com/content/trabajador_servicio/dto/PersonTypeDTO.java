package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonTypeDTO {

    private Integer id;

    private String person_Type;

    private List<IdentificationType> identificationTypes;

    private Integer id_State;
}
