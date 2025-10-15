package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.StateEntityResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateEntityMapper extends Convert<StateEntity,UserClientRequestDTO, StateEntityResponseDTO> {

    StateEntityMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(StateEntityMapper.class);


    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "state_entity_name", target = "name")
    @Override
    StateEntityResponseDTO toDTO(StateEntity modelo);



    @Override
    StateEntity toModel(UserClientRequestDTO dto);
}
