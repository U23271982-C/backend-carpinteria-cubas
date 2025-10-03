package com.content.inventory_matter_service.mapper;

import com.content.inventory_matter_service.dto.Request.MatterRequestDTO;
import com.content.inventory_matter_service.dto.Response.MatterResponseDTO;
import com.content.inventory_matter_service.mapper.convert.Convert;
import com.content.inventory_matter_service.model.Matter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatterMapper
        extends Convert<Matter, MatterRequestDTO, MatterResponseDTO> {

    @Override
    MatterResponseDTO toDTO(Matter model);

    @Override
    Matter toModel(MatterRequestDTO dto);
}
