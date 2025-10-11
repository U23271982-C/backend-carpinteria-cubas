package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.DistrictRequestDTO;
import com.content.employee_service.dto.response.DistrictResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Distric;

public interface DistrictMapper
        extends Convert<Distric, DistrictRequestDTO, DistrictResponseDTO> {
    @Override
    DistrictResponseDTO toDTO(Distric model);

    @Override
    Distric toModel(DistrictRequestDTO dto);
}
