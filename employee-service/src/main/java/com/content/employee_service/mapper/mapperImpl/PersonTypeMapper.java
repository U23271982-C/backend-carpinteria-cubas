package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.PersonTypeRequestDTO;
import com.content.employee_service.dto.response.PersonTypeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.PersonType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonTypeMapper
        extends Convert<PersonType, PersonTypeRequestDTO, PersonTypeResponseDTO>,
        UpdatePatch<PersonTypeRequestDTO, PersonType> {
    @Mapping(target = "person_type_name", source = "person_type_name")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Override
    PersonTypeResponseDTO toDTO(PersonType model);

    @Mapping(target = "person_type_name", source = "person_type_name")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    PersonType toModel(PersonTypeRequestDTO dto);
}
