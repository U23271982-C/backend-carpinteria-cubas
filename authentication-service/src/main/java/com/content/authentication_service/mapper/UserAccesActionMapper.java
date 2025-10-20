package com.content.authentication_service.mapper;
import com.content.authentication_service.dto.request.UserAccessActionRequestDTO;
import com.content.authentication_service.dto.response.UserAccessActionResponseDTO;
import com.content.authentication_service.model.UserAccessAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAccesActionMapper {

    UserAccesActionMapper INSTANCE = Mappers.getMapper(UserAccesActionMapper.class);

    @Mapping (source = "uuid", target = "uuid")
    @Mapping (source = "user_module_access_id.module_id.module_name", target = "module_name")
    @Mapping (source = "user_module_access_id.user_employee_id.user_employee_name", target = "employee_name")
    @Mapping (source = "action_id.action_name", target = "action_name")
    UserAccessActionResponseDTO toDTO(UserAccessAction modelo);


    @Mapping(target = "user_access_action_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "action_id.uuid", source = "action_uuid")
    @Mapping(target = "user_module_access_id.uuid", source = "user_module_access_uuid")
    UserAccessAction toModel(UserAccessActionRequestDTO dto);


}
