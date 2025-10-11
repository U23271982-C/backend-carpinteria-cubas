package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.PersonTypeRequestDTO;
import com.content.employee_service.dto.response.PersonTypeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.PersonType;

public interface PersonTypeMapper
        extends Convert<PersonType, PersonTypeRequestDTO, PersonTypeResponseDTO> {
    @Override
    PersonTypeResponseDTO toDTO(PersonType model);

    @Override
    PersonType toModel(PersonTypeRequestDTO dto);
}
