package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {

    private Integer id;

    private String name_Province;

    private Integer id_Department;

    private List<DistrictDTO> districts;

    private Integer id_State;
}
