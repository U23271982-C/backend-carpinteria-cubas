package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.DistrictRequestDTO;
import com.content.employee_service.dto.response.DistrictResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.Distric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper
        extends Convert<Distric, DistrictRequestDTO, DistrictResponseDTO>,
        UpdatePatch<DistrictRequestDTO, Distric> {
    @Mapping(target = "district_name", source = "district_name")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Mapping(target = "uuid", source = "uuid")
    @Override
    DistrictResponseDTO toDTO(Distric model);

    @Mapping(target = "district_name", source = "district_name")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Distric toModel(DistrictRequestDTO dto);
}
