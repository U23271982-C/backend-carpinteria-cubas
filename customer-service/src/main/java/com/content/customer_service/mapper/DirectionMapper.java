package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para Direction - ACTUALIZADO PARA TRABAJAR CON UUIDs
 */
@Mapper(componentModel = "spring")
public interface DirectionMapper extends Convert<Direction, DirectionRequestDTO, DirectionResponseDTO> {

    /**
     * Convierte Direction a DirectionResponseDTO - SOLO UUIDs
     */
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "direction_name", target = "directionName")
    @Mapping(source = "address_line_1", target = "addressLine1")
    @Mapping(source = "address_line_2", target = "addressLine2")
    @Mapping(source = "client_id.uuid", target = "clientUuid")
    @Mapping(source = "client_id.client_name", target = "clientName")
    @Mapping(source = "direction_type_id.uuid", target = "directionTypeUuid")
    @Mapping(source = "direction_type_id.type_name", target = "directionTypeName")
    @Mapping(source = "district_id.uuid", target = "districtUuid")
    @Mapping(source = "district_id.district_name", target = "districtName")
    @Mapping(source = "district_id.province_id.uuid", target = "provinceUuid")
    @Mapping(source = "district_id.province_id.province_name", target = "provinceName")
    @Mapping(source = "district_id.province_id.department_id.uuid", target = "departmentUuid")
    @Mapping(source = "district_id.province_id.department_id.department_name", target = "departmentName")
    @Mapping(source = "state_entity_id.uuid", target = "stateEntityUuid")
    @Mapping(source = "state_entity_id.state_name", target = "stateName")
    @Override
    DirectionResponseDTO toDTO(Direction modelo);

    /**
     * Convierte DirectionRequestDTO a Direction
     */
    @Mapping(target = "direction_id", ignore = true) // ID interno se genera
    @Mapping(target = "uuid", ignore = true) // UUID se genera en BaseEntity
    @Mapping(source = "directionName", target = "direction_name")
    @Mapping(source = "addressLine1", target = "address_line_1")
    @Mapping(source = "addressLine2", target = "address_line_2")
    @Mapping(target = "client_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "direction_type_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "district_id", ignore = true) // Se asigna en servicio por UUID
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en servicio por UUID
    @Override
    Direction toModel(DirectionRequestDTO dto);
}
