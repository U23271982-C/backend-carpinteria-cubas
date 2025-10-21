package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.IdentificationRequestDTO;
import com.content.customer_service.dto.request.IdentificationTypeRequestDTO;
import com.content.customer_service.dto.response.IdentificationResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Identification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Identification
 */
@Mapper(componentModel = "spring")
public interface IdentificationMapper
        extends Convert<Identification, IdentificationRequestDTO, IdentificationResponseDTO>,
        UpdatePatch<IdentificationRequestDTO, Identification> {

    /**
     * Convierte Identification a IdentificationResponseDTO - SOLO UUIDs
     */
    @Mapping(target = "identification_uuid", source = "uuid")
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "identification_type_uuid", source = "identification_type_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    IdentificationResponseDTO toDTO(Identification modelo);

    /**
     * Convierte IdentificationRequestDTO a Identification
     */
    @Mapping(target = "identification_number", source = "identification_number")
    @Mapping(target = "identification_type_id.uuid", source = "identification_type_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Identification toModel(IdentificationRequestDTO dto);
}
