package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.EmployeeRequestDTO;
import com.content.employee_service.dto.response.EmployeeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper
        extends Convert<Employee, EmployeeRequestDTO, EmployeeResponseDTO> {

    @Mapping(target = "identification_type_id", source = "identification_type_id.employee_id")
    @Mapping(target = "post_id", source = "post_id.post_id")
    @Mapping(target = "contract_id", source = "distric_id.distric_id")
    @Mapping(target = "distric_id", source = "distric_id.distric_id")
    @Override
    EmployeeResponseDTO toDTO(Employee modelo);

    @Override
    Employee toModel(EmployeeRequestDTO dto);
}
