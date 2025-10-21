package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Contact
 */
@Mapper(componentModel = "spring")
public interface ContactMapper
        extends Convert<Contact, ContactRequestDTO, ContactResponseDTO>,
        UpdatePatch<ContactRequestDTO, Contact> {

    /**
     * Convierte Contact a ContactResponseDTO - SOLO UUIDs
     */
    @Mapping(target = "contact_uuid", source = "uuid")
    @Mapping(target = "client_uuid", source = "client_id.uuid")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    ContactResponseDTO toDTO(Contact modelo);

    /**
     * Convierte ContactRequestDTO a Contact
     */
    @Mapping(target = "client_id.uuid", source = "client_uuid")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Contact toModel(ContactRequestDTO dto);
}
