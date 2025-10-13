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

    @Mapping(target = "employee_name", source = "employee_name")
    @Mapping(target = "employee_last_name", source = "employee_last_name")
    @Mapping(target = "birth_date", source = "birth_date")
    @Mapping(target = "register_date", source = "register_date")
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "direction_email", source = "direction_email")
    @Mapping(target = "identification_type_id", source = "identification_type_id.identification_type_id")
    @Mapping(target = "post_id", source = "post_id.post_id")
    @Mapping(target = "contract_id", source = "contract_id.contract_id")
    @Mapping(target = "distric_id", source = "distric_id.distric_id")
    @Override
    EmployeeResponseDTO toDTO(Employee modelo);

    @Mapping(target = "employee_name", source = "employee_name")
    @Mapping(target = "employee_last_name", source = "employee_last_name")
    @Mapping(target = "birth_date", source = "birth_date")
    @Mapping(target = "register_date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "direction_email", source = "direction_email")
    @Mapping(target = "identification_type_id.identification_type_id", source = "identification_type_id")
    @Mapping(target = "post_id.post_id", source = "post_id")
    @Mapping(target = "contract_id.contract_id", source = "contract_id")
    @Mapping(target = "distric_id.distric_id", source = "distric_id")
    @Override
    Employee toModel(EmployeeRequestDTO dto);
}
