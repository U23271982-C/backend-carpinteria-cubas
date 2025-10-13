package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContactRequestDTO;
import com.content.employee_service.dto.response.ContactResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper
        extends Convert<Contact, ContactRequestDTO, ContactResponseDTO> {
    @Mapping(target = "employee_id", source = "employee_id.employee_id")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Override
    ContactResponseDTO toDTO(Contact model);

    @Mapping(target = "employee_id.employee_id", source = "employee_id")
    @Mapping(target = "phone_number", source = "phone_number")
    @Mapping(target = "email", source = "email")
    @Override
    Contact toModel(ContactRequestDTO dto);
}
