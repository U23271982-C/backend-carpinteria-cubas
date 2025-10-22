package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ClientTypeRequestDTO;
import com.content.customer_service.dto.response.ClientTypeResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.ClientType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientTypeMapper
        extends Convert<ClientType, ClientTypeRequestDTO, ClientTypeResponseDTO>,
        UpdatePatch<ClientTypeRequestDTO, ClientType> {

    @Mapping(target = "client_type_uuid", source = "uuid")
    @Mapping(target = "client_type_name", source = "client_type_name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    ClientTypeResponseDTO toDTO(ClientType model);

    @Mapping(target = "client_type_name", source = "client_type_name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    ClientType toModel(ClientTypeRequestDTO dto);
}
