package com.content.authentication_service.mapper.mapperImpl;

import com.content.authentication_service.model.UserClient;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface UserClientMapper {
    @Override
    @Mappings({
            // Mapea el campo `state_entity_name` del objeto anidado a `state` en el DTO.
            @Mapping(source = "state_entity_id.state_entity_name", target = "state"),
            @Mapping(source = "user_client_email", target = "email"),
            @Mapping(source = "user_client_full_name", target = "fullName"),
            @Mapping(source = "user_client_phone", target = "phone"),
            @Mapping(source = "user_client_address", target = "address")
    })
    UserClientResponse toDTO(UserClient model);

    @Override
    @Mappings({
            // Ignoramos campos que se manejarán en la capa de servicio.
            @Mapping(target = "user_client_id", ignore = true),
            @Mapping(target = "fire_base_uid", ignore = true),
            @Mapping(target = "state_entity_id", ignore = true), // El servicio se encargará de asignar el estado.
            @Mapping(source = "email", target = "user_client_email"),
            @Mapping(source = "fullName", target = "user_client_full_name"),
            @Mapping(source = "phone", target = "user_client_phone"),
            @Mapping(source = "address", target = "user_client_address")
    })
    UserClient toModel(UserClientRequest dto);
}