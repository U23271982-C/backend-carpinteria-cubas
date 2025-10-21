package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Direction
 */
@Mapper(componentModel = "spring")
public interface DirectionMapper
        extends Convert<Direction, DirectionRequestDTO, DirectionResponseDTO>,
        UpdatePatch<DirectionRequestDTO, Direction> {

    /**
     * Convierte Direction a DirectionResponseDTO - SOLO UUIDs
     */
    @Mapping(target = "direction_uuid", source = "uuid")
    @Mapping(target = "client_uuid", source = "client_id.uuid")
    @Mapping(target = "direction_type_uuid", source = "directionType_id.uuid")
    @Mapping(target = "direction_name", source = "direction_name")
    @Mapping(target = "direction_number", source = "direction_number")
    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "district_uuid", source = "district_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    DirectionResponseDTO toDTO(Direction modelo);

    /**
     * Convierte DirectionRequestDTO a Direction
     */
    @Mapping(target = "client_id.uuid", source = "client_uuid")
    @Mapping(target = "directionType_id.uuid", source = "direction_type_uuid")
    @Mapping(target = "direction_name", source = "direction_name")
    @Mapping(target = "direction_number", source = "direction_number")
    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "district_id.uuid", source = "district_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Direction toModel(DirectionRequestDTO dto);
}
