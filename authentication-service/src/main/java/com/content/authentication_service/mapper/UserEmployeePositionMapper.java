package com.content.authentication_service.mapper;
import com.content.authentication_service.dto.request.UserEmployeePositionRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeePositionResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.UserEmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEmployeePositionMapper extends Convert<UserEmployeePosition, UserEmployeePositionRequestDTO, UserEmployeePositionResponseDTO > {

    UserEmployeePositionMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserEmployeePositionMapper.class);

    @Mapping (source = "uuid", target = "uuid")
    @Mapping (source = "position_name", target = "positionName")
    @Mapping(source = "position_description", target = "positionDescription")
    @Mapping(source = "state_entity_id.state_entity_name", target = "nameStateEntity")
    @Override
    UserEmployeePositionResponseDTO toDTO(UserEmployeePosition modelo);

    @Mapping(target = "user_employee_position_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "position_name", source = "positionName")
    @Mapping(target = "position_description", source = "positionDescription")
    @Mapping(target = "state_entity_id.uuid", source = "stateEntityuuid") // Asignamos el UUID del estado directamente
    @Override
    UserEmployeePosition toModel(UserEmployeePositionRequestDTO dto);


}
