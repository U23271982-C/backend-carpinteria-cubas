package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.IdentificationRequestDTO;
import com.content.customer_service.dto.response.IdentificationResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Identification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para conversión entre Identification, IdentificationRequestDTO y IdentificationResponseDTO
 */
@Mapper(componentModel = "spring")
public interface IdentificationMapper extends Convert<Identification, IdentificationRequestDTO, IdentificationResponseDTO> {

    /**
     * Convierte un modelo Identification a IdentificationResponseDTO
     */
    @Mapping(source = "identification_type_id.identification_type_id", target = "identification_type_id")
    @Mapping(source = "identification_type_id.identification_type_name", target = "identification_type_name")
    @Mapping(source = "state_entity_id.state_entity_id", target = "state_entity_id")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state_entity_name")
    @Override
    IdentificationResponseDTO toDTO(Identification modelo);

    /**
     * Convierte un IdentificationRequestDTO a modelo Identification
     */
    @Mapping(target = "identification_id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en el servicio
    @Mapping(source = "identification_type_id", target = "identification_type_id.identification_type_id")
    @Override
    Identification toModel(IdentificationRequestDTO dto);
}

