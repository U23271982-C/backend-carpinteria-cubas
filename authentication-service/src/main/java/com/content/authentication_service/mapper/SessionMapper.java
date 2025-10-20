package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.SessionRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionMapper extends Convert<Session,SessionRequestDTO, SessionResponseDTO> {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Override
    SessionResponseDTO toDTO(Session modelo);

    @Override
    Session toModel(SessionRequestDTO dto);

}
