package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ProvinceRequestDTO;
import com.content.customer_service.dto.response.ProvinceResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProvinceMapper
        extends Convert<Province, ProvinceRequestDTO, ProvinceResponseDTO>,
        UpdatePatch<ProvinceRequestDTO, Province> {

    @Mapping(target = "province_uuid", source = "uuid")
    @Mapping(target = "province_name", source = "province_name")
    @Mapping(target = "department_uuid", source = "department_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Override
    ProvinceResponseDTO toDTO(Province model);

    @Mapping(target = "province_name", source = "province_name")
    @Mapping(target = "department_id.uuid", source = "department_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Province toModel(ProvinceRequestDTO dto);
}
