package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.DistrictRequestDTO;
import com.content.employee_service.dto.response.DistrictResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Distric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper
        extends Convert<Distric, DistrictRequestDTO, DistrictResponseDTO> {
    @Mapping(target = "district_name", source = "district_name")
    @Override
    DistrictResponseDTO toDTO(Distric model);

    @Mapping(target = "district_name", source = "district_name")
    @Override
    Distric toModel(DistrictRequestDTO dto);
}
