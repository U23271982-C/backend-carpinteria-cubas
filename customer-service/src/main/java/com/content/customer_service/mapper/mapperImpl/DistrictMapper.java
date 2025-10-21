package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.DistrictRequestDTO;
import com.content.customer_service.dto.response.DistrictResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper
        extends Convert<District, DistrictRequestDTO, DistrictResponseDTO>,
        UpdatePatch<DistrictRequestDTO, District> {

    @Mapping(target = "district_uuid", source = "district_uuid")
    @Mapping(target = "district_name", source = "district_name")
    @Mapping(target = "province_uuid", source = "province_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_name")
    @Override
    DistrictResponseDTO toDTO(District model);

    @Mapping(target = "district_name", source = "district_name")
    @Mapping(target = "province_id.uuid", source = "province_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    District toModel(DistrictRequestDTO dto);
}
