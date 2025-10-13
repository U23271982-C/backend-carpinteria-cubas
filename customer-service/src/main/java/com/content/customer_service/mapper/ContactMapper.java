package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para conversión entre Contact, ContactRequestDTO y ContactResponseDTO
 */
@Mapper(componentModel = "spring")
public interface ContactMapper extends Convert<Contact, ContactRequestDTO, ContactResponseDTO> {

    /**
     * Convierte un modelo Contact a ContactResponseDTO
     */
    @Mapping(source = "client_id.client_id", target = "client_id")
    @Mapping(source = "client_id.client_name", target = "client_name")
    @Mapping(source = "state_entity_id.state_entity_id", target = "state_entity_id")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state_entity_name")
    @Override
    ContactResponseDTO toDTO(Contact modelo);

    /**
     * Convierte un ContactRequestDTO a modelo Contact
     */
    @Mapping(target = "contact_id", ignore = true)
    @Mapping(target = "client_id", ignore = true) // Se asigna en el servicio
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en el servicio
    @Override
    Contact toModel(ContactRequestDTO dto);
}

