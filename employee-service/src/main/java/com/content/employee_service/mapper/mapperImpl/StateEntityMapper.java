package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.response.StateEntityResponseDTO;
import com.content.employee_service.mapper.convert.ConvertDTO;
import com.content.employee_service.model.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateEntityMapper extends ConvertDTO <StateEntity, StateEntityResponseDTO> {
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(target = "state_name", source = "state_entity_name")
    @Override
    StateEntityResponseDTO toDTO(StateEntity model);
}
