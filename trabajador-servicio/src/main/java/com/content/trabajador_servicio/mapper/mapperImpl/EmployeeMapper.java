package com.content.trabajador_servicio.mapper.mapperImpl;

import com.content.trabajador_servicio.dto.Request.EmployeeRequestDTO;
import com.content.trabajador_servicio.dto.Response.EmployeeResponseDTO;
import com.content.trabajador_servicio.mapper.convert.Convert;
import com.content.trabajador_servicio.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends Convert<Employee, EmployeeRequestDTO, EmployeeResponseDTO> {
    @Override
    EmployeeResponseDTO toDTO(Employee modelo);

    @Override
    Employee toModel(EmployeeRequestDTO dto);
}
