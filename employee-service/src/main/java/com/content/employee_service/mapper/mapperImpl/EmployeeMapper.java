package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.EmployeeRequestDTO;
import com.content.employee_service.dto.response.EmployeeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper
        extends Convert<Employee, EmployeeRequestDTO, EmployeeResponseDTO>,
        UpdatePatch<EmployeeRequestDTO, Employee> {

    @Mapping(target = "employee_name", source = "employee_name")
    @Mapping(target = "employee_last_name", source = "employee_last_name")
    @Mapping(target = "birth_date", source = "birth_date")
    @Mapping(target = "register_date", source = "register_date")
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "direction_name", source = "direction_name")
    @Mapping(target = "identification_type_uuid", source = "identification_type_id.uuid")
    @Mapping(target = "post_uuid", source = "post_id.uuid")
    @Mapping(target = "contract_uuid", source = "contract_id.uuid")
    @Mapping(target = "distric_uuid", source = "distric_id.uuid")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Override
    EmployeeResponseDTO toDTO(Employee modelo);

    @Mapping(target = "employee_name", source = "employee_name")
    @Mapping(target = "employee_last_name", source = "employee_last_name")
    @Mapping(target = "birth_date", source = "birth_date")
    @Mapping(target = "register_date", expression = "java(com.content.employee_service.utility.TimeHelper.getCurrentLocalDateTime())")
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "direction_email", source = "direction_email")
    @Mapping(target = "direction_name", source = "direction_name")
    @Mapping(target = "identification_type_id.uuid", source = "identification_type_uuid")
    @Mapping(target = "post_id.uuid", source = "post_id_uuid")
    @Mapping(target = "contract_id.uuid", source = "contract_id_uuid")
    @Mapping(target = "distric_id.uuid", source = "distric_id_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Employee toModel(EmployeeRequestDTO dto);
}
