package com.content.customer_service.mapper;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para conversión entre Direction, DirectionRequestDTO y DirectionResponseDTO
 */
@Mapper(componentModel = "spring")
public interface DirectionMapper extends Convert<Direction, DirectionRequestDTO, DirectionResponseDTO> {

    /**
     * Convierte un modelo Direction a DirectionResponseDTO
     */
    @Mapping(source = "client_id.client_id", target = "client_id")
    @Mapping(source = "client_id.client_name", target = "client_name")
    @Mapping(source = "direction_type_id.direction_type_id", target = "direction_type_id")
    @Mapping(source = "direction_type_id.description", target = "direction_type_description")
    @Mapping(source = "district_id.district_id", target = "district_id")
    @Mapping(source = "district_id.district_name", target = "district_name")
    @Mapping(source = "district_id.province_id.province_id", target = "province_id")
    @Mapping(source = "district_id.province_id.province_name", target = "province_name")
    @Mapping(source = "district_id.province_id.department_id.department_id", target = "department_id")
    @Mapping(source = "district_id.province_id.department_id.department_name", target = "department_name")
    @Mapping(source = "state_entity_id.state_entity_id", target = "state_entity_id")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state_entity_name")
    @Override
    DirectionResponseDTO toDTO(Direction modelo);

    /**
     * Convierte un DirectionRequestDTO a modelo Direction
     * Los campos de departamento, provincia y distrito se manejan en el servicio
     */
    @Mapping(target = "direction_id", ignore = true)
    @Mapping(target = "client_id", ignore = true) // Se asigna en el servicio
    @Mapping(target = "direction_type_id", ignore = true) // Se asigna en el servicio
    @Mapping(target = "district_id", ignore = true) // Se busca o crea en el servicio
    @Mapping(target = "state_entity_id", ignore = true) // Se asigna en el servicio
    @Override
    Direction toModel(DirectionRequestDTO dto);
}

