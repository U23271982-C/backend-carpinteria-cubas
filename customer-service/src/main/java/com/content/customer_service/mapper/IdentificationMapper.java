package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.IdentificationRequestDTO;
import com.content.customer_service.dto.response.IdentificationResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Identification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Identification - ACTUALIZADO PARA TRABAJAR CON UUIDs
 */
@Mapper(componentModel = "spring")
public interface IdentificationMapper extends Convert<Identification, IdentificationRequestDTO, IdentificationResponseDTO> {

    /**
     * Convierte Identification a IdentificationResponseDTO - SOLO UUIDs
     */
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "identification_number", target = "identificationNumber")
    @Mapping(source = "identification_type_id.uuid", target = "identificationTypeUuid")
    @Mapping(source = "identification_type_id.type_name", target = "identificationTypeName")
    @Mapping(source = "person_type_id.uuid", target = "personTypeUuid")
    @Mapping(source = "person_type_id.type_name", target = "personTypeName")
    @Mapping(source = "state_entity_id.uuid", target = "stateEntityUuid")
    @Mapping(source = "state_entity_id.state_name", target = "stateName")
    @Override
    IdentificationResponseDTO toDTO(Identification modelo);

    /**
     * Convierte IdentificationRequestDTO a Identification
     */
    @Mapping(target = "identification_id", ignore = true) // ID interno se genera
    @Mapping(target = "uuid", ignore = true) // UUID se genera en BaseEntity
    @Mapping(source = "identificationNumber", target = "identification_number")
    @Mapping(target = "identification_type_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "person_type_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en servicio por UUID
    @Override
    Identification toModel(IdentificationRequestDTO dto);
}
