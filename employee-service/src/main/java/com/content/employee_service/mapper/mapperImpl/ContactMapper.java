package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContactRequestDTO;
import com.content.employee_service.dto.response.ContactResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper
        extends Convert<Contact, ContactRequestDTO, ContactResponseDTO>,
        UpdatePatch<ContactRequestDTO, Contact> {
    @Mapping(target = "employee_id_uuid", source = "employee_id.uuid")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Override
    ContactResponseDTO toDTO(Contact model);

    @Mapping(target = "employee_id.uuid", source = "employee_id_uuid")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Contact toModel(ContactRequestDTO dto);
}
