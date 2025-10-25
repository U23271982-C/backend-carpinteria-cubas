package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.SessionRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserModuleAccessMapper.class})
public interface SessionMapper extends Convert<Session,SessionRequestDTO, SessionResponseDTO> {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);


    @Mapping(source = "user_employee_id.full_name", target = "full_name")
    @Mapping(source = "user_employee_id.username", target = "user_employee_name")
    @Mapping(source = "user_employee_id.user_employee_position_id.position_name", target = "user_position")
    @Mapping(source = "user_employee_id.user_employee_phone", target = "user_phone")
    @Mapping(source = "sesion_date", target = "session_date")
    @Mapping(source = "user_employee_id.user_module_accesses", target = "modules_access")
    @Mapping(source = "succesed", target = "succesed")
    @Override
    SessionResponseDTO toDTO(Session modelo);


    @Mapping(target = "user_employee_id.username", source = "username")
    @Mapping(target = "user_employee_id.password", source = "password")
    @Override
    Session toModel(SessionRequestDTO dto);

}
