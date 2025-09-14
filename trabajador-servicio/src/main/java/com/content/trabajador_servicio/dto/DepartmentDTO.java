package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Integer id;

    private String nameDepartment;

    private List<Province> provinces;

    private Integer id_State;
}
