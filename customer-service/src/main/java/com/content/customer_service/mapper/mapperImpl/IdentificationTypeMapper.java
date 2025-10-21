package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.IdentificationTypeRequestDTO;
import com.content.customer_service.dto.response.IdentificationTypeResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.IdentificationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper
        extends Convert<IdentificationType, IdentificationTypeRequestDTO, IdentificationTypeResponseDTO>,
        UpdatePatch<IdentificationTypeRequestDTO, IdentificationType> {

    @Mapping(target = "identification_type_uuid", source = "uuid")
    @Mapping(target = "identification_type_name", source = "identification_type_name")
    @Mapping(target = "person_type_uuid", source = "person_type_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    IdentificationTypeResponseDTO toDTO(IdentificationType model);

    @Mapping(target = "identification_type_name", source = "identification_type_name")
    @Mapping(target = "person_type_id.uuid", source = "person_type_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    IdentificationType toModel(IdentificationTypeRequestDTO dto);
}
