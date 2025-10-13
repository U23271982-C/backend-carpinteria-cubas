package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para conversión entre Client, ClientRequestDTO y ClientResponseDTO
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends Convert<Client, ClientRequestDTO, ClientResponseDTO> {

    /**
     * Convierte un modelo Client a ClientResponseDTO
     */
    @Mapping(source = "client_type_id.client_type_id", target = "client_type_id")
    @Mapping(source = "client_type_id.client_type_name", target = "client_type_name")
    @Mapping(source = "identification_id.identification_id", target = "identification_id")
    @Mapping(source = "identification_id.identification_doc", target = "identification_doc")
    @Mapping(source = "state_entity_id.state_entity_id", target = "state_entity_id")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state_entity_name")
    @Override
    ClientResponseDTO toDTO(Client modelo);

    /**
     * Convierte un ClientRequestDTO a modelo Client
     * Se ignoran campos autogenerados como ID y fecha de registro
     */
    @Mapping(target = "client_id", ignore = true)
    @Mapping(target = "registration_date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "directions", ignore = true)
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en el servicio (estado activo por defecto)
    @Mapping(source = "client_type_id", target = "client_type_id.client_type_id")
    @Mapping(source = "identification_id", target = "identification_id.identification_id")
    @Override
    Client toModel(ClientRequestDTO dto);
}

