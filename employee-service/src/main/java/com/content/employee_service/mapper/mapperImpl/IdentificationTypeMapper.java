package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.IdentificationTypeRequestDTO;
import com.content.employee_service.dto.response.IdentificationTypeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.IdentificationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper
        extends Convert<IdentificationType, IdentificationTypeRequestDTO, IdentificationTypeResponseDTO> {
    @Mapping(target = "person_type_id", source = "person_type_id.person_type_id")
    @Override
    IdentificationTypeResponseDTO toDTO(IdentificationType model);

    @Override
    IdentificationType toModel(IdentificationTypeRequestDTO dto);
}
