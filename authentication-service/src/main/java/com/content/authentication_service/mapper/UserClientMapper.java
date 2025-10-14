package com.content.authentication_service.mapper;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.UserClientResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserClientMapper extends Convert<UserClient, UserClientRequestDTO, UserClientResponseDTO> {

    UserClientMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserClientMapper.class);

    @Mapping(source = "fireBaseUid", target = "fire_base_uid") // Mapeamos el firebaseUid del modelo
    @Mapping(source = "user_client_email", target = "email")
    @Mapping(source = "user_client_full_name", target = "fullName")
    @Mapping(source = "user_client_phone", target = "phone")
    @Mapping(source = "user_client_address", target = "address")
    @Mapping(source = "state_entity_id.state_entity_id", target = "state")
    @Mapping(source = "user_client_created_at", target = "createdAt")
    @Override
    UserClientResponseDTO toDTO(UserClient modelo);

    @Mapping(target = "user_client_id", ignore = true)
    @Mapping(target = "fireBaseUid", source = "firebaseUid") // Mapeamos el firebaseUid del DTO
    @Mapping(target = "user_client_email", source = "email")
    @Mapping(target = "user_client_full_name", source = "fullName")
    @Mapping(target = "user_client_phone", source = "phone")
    @Mapping(target = "user_client_address", source = "address")
    @Mapping(target = "state_entity_id", ignore = true)
    @Mapping(target = "user_client_created_at", expression = "java(java.time.LocalDate.now())")
    @Override
    UserClient toModel(UserClientRequestDTO dto);
}
