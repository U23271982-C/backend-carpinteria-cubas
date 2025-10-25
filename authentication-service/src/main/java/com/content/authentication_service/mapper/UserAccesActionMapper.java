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
    @Mapping (source = "umaId.module_id.name", target = "module_name")
    @Mapping (source = "umaId.user_employee_id.username", target = "employee_name")
    @Mapping (source = "actionId.action_name", target = "action_name")
    UserAccessActionResponseDTO toDTO(UserAccessAction modelo);


    @Mapping(target = "user_access_action_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "actionId.uuid", source = "action_uuid")
    @Mapping(target = "umaId.uuid", source = "user_module_access_uuid")
    UserAccessAction toModel(UserAccessActionRequestDTO dto);


}
