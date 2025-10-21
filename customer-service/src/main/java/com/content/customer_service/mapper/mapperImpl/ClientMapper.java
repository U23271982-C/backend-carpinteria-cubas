package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para conversión entre Client, ClientRequestDTO y ClientResponseDTO
 * ACTUALIZADO PARA TRABAJAR CON UUIDs
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends Convert<Client, ClientRequestDTO, ClientResponseDTO> {

    /**
     * Convierte un modelo Client a ClientResponseDTO
     * SOLO EXPONE UUIDs, NUNCA IDs INTERNOS
     */
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "client_name", target = "clientName")
    @Mapping(source = "client_last_name", target = "clientLastName")
    @Mapping(source = "registration_date", target = "registrationDate")
    @Mapping(source = "client_type_id.uuid", target = "clientTypeUuid")
    @Mapping(source = "client_type_id.type_name", target = "clientTypeName")
    @Mapping(source = "identification_id.uuid", target = "identificationUuid")
    @Mapping(source = "identification_id.identification_number", target = "identificationNumber")
    @Mapping(source = "identification_id.identification_type_id.type_name", target = "identificationTypeName")
    @Mapping(source = "state_entity_id.uuid", target = "stateEntityUuid")
    @Mapping(source = "state_entity_id.state_name", target = "stateName")
    @Mapping(target = "contacts", ignore = true) // Se mapean por separado
    @Mapping(target = "directions", ignore = true) // Se mapean por separado
    @Override
    ClientResponseDTO toDTO(Client modelo);

    /**
     * Convierte un ClientRequestDTO a modelo Client
     * IMPORTANTE: No mapea UUIDs ni IDs internos, solo datos de negocio
     */
    @Mapping(target = "client_id", ignore = true) // ID interno se genera automáticamente
    @Mapping(target = "uuid", ignore = true) // UUID se genera automáticamente en BaseEntity
    @Mapping(source = "clientName", target = "client_name")
    @Mapping(source = "clientLastName", target = "client_last_name")
    @Mapping(target = "registration_date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "client_type_id", ignore = true) // Se asigna en el servicio usando UUID
    @Mapping(target = "identification_id", ignore = true) // Se asigna en el servicio usando UUID
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en el servicio usando UUID
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "directions", ignore = true)
    @Override
    Client toModel(ClientRequestDTO dto);
}
