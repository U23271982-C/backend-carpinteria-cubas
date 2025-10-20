package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.UserEmployeeRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeeResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.UserEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserModuleAccessMapper.class})
public interface UserEmployeeMapper extends Convert<UserEmployee, UserEmployeeRequestDTO, UserEmployeeResponseDTO> {

    UserEmployeeMapper INSTANCE = Mappers.getMapper(UserEmployeeMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "full_name", target = "full_name")
    @Mapping(source = "user_employee_name", target = "user_name")
    @Mapping(source = "user_employee_position_id.position_name", target = "position")
    @Mapping(source = "user_employee_phone", target = "phone")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state")
    @Mapping(source = "user_module_accesses", target = "moduleAccesses")
    @Override
    UserEmployeeResponseDTO toDTO (UserEmployee model);


    @Mapping(target = "user_employee_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "full_name", source = "full_name")
    @Mapping(target = "user_employee_name", source = "user_name")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "user_employee_position_id.uuid", source = "positionUUID")
    @Mapping(target = "user_employee_phone", source = "phone")
    @Mapping(target = "state_entity_id.uuid" ,source = "stateUUID")
    @Override
    UserEmployee toModel (UserEmployeeRequestDTO dto);


}
