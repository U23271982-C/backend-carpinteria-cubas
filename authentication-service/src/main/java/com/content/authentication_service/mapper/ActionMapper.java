package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.ActionRequestDTO;
import com.content.authentication_service.dto.response.ActionResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActionMapper extends Convert<Action, ActionRequestDTO, ActionResponseDTO> {


    @Mapping(source = "uuid", target= "uuid")
    @Mapping(source = "action_name", target= "action_name")
    @Mapping(source = "action_description", target= "action_description")
    @Override
    ActionResponseDTO toDTO(Action modelo);

    @Override
    Action toModel(ActionRequestDTO dto);

}
