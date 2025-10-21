package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.DirectionTypeRequestDTO;
import com.content.customer_service.dto.response.DirectionTypeResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.DirectionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectionTypeMapper
        extends Convert<DirectionType, DirectionTypeRequestDTO, DirectionTypeResponseDTO>,
        UpdatePatch<DirectionTypeRequestDTO, DirectionType> {

    @Mapping(target = "direction_type_uuid", source = "uuid")
    @Mapping(target = "direction_type_name", source = "direction_type_name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    DirectionTypeResponseDTO toDTO(DirectionType model);


    @Mapping(target = "direction_type_name", source = "direction_type_name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    DirectionType toModel(DirectionTypeRequestDTO dto);
}
