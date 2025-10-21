package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Contact - ACTUALIZADO PARA TRABAJAR CON UUIDs
 */
@Mapper(componentModel = "spring")
public interface ContactMapper extends Convert<Contact, ContactRequestDTO, ContactResponseDTO> {

    /**
     * Convierte Contact a ContactResponseDTO - SOLO UUIDs
     */
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "phone_number", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "client_id.uuid", target = "clientUuid")
    @Mapping(source = "client_id.client_name", target = "clientName")
    @Mapping(source = "state_entity_id.uuid", target = "stateEntityUuid")
    @Mapping(source = "state_entity_id.state_name", target = "stateName")
    @Override
    ContactResponseDTO toDTO(Contact modelo);

    /**
     * Convierte ContactRequestDTO a Contact
     */
    @Mapping(target = "contact_id", ignore = true) // ID interno se genera
    @Mapping(target = "uuid", ignore = true) // UUID se genera en BaseEntity
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "client_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en servicio por UUID
    @Override
    Contact toModel(ContactRequestDTO dto);
}
