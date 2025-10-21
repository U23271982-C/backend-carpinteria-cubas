package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.DepartmentRequestDTO;
import com.content.customer_service.dto.response.DepartmentResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper
        extends Convert<Department, DepartmentRequestDTO, DepartmentResponseDTO>,
        UpdatePatch<DepartmentRequestDTO, Department> {

    @Mapping(target = "department_uuid", source = "uuid")
    @Mapping(target = "department_name", source = "department_name")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    DepartmentResponseDTO toDTO(Department modelo);

    @Mapping(target = "department_name", source = "department_name")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    default Department toModel(DepartmentRequestDTO dto) {
        return null;
    }

}
