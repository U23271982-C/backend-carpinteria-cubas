package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.IdentificationTypeRequestDTO;
import com.content.employee_service.dto.response.IdentificationTypeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.IdentificationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper
        extends Convert<IdentificationType, IdentificationTypeRequestDTO, IdentificationTypeResponseDTO> {
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "identification_type_name", source = "identification_type_name")
    @Mapping(target = "person_type_uuid", source = "person_type_id.person_type_uuid")
    @Mapping(target = "state_entity", source = "state_entity.state_entity_name")
    @Override
    IdentificationTypeResponseDTO toDTO(IdentificationType model);

    @Mapping(target = "identification_type_name", source = "identification_type_name")
    @Mapping(target = "person_type_id.person_type_uuid", source = "person_type_id")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    IdentificationType toModel(IdentificationTypeRequestDTO dto);
}
