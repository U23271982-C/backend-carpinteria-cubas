package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.UserModuleAccessRequestDTO;
import com.content.authentication_service.dto.response.UserModuleAccessResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.UserModuleAccess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserAccesActionMapper.class})
public interface UserModuleAccessMapper extends Convert<UserModuleAccess, UserModuleAccessRequestDTO, UserModuleAccessResponseDTO> {

    UserModuleAccessMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserModuleAccessMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "user_employee_id.user_employee_name", target = "employee_name")
    @Mapping(source = "module_id.module_name", target = "module_name")
    @Mapping(source = "user_access_actions", target = "access_permissions")
    @Override
    UserModuleAccessResponseDTO toDTO(UserModuleAccess entity);


    @Mapping(target = "user_module_access_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "user_employee_id.uuid", source = "employee_uuid")
    @Mapping(target = "module_id.uuid", source="module_uuid")
    @Override
    UserModuleAccess toModel(UserModuleAccessRequestDTO dto);
}
