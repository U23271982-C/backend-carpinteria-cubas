package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.Request.EmployeeRequestDTO;
import com.content.employee_service.dto.Response.EmployeeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends Convert<Employee, EmployeeRequestDTO, EmployeeResponseDTO> {
    @Override
    EmployeeResponseDTO toDTO(Employee modelo);

    @Override
    Employee toModel(EmployeeRequestDTO dto);
}
