package com.content.inventario_stock_servicio.mapper;

import com.content.inventario_stock_servicio.dto.Request.MatterRequestDTO;
import com.content.inventario_stock_servicio.dto.Response.MatterResponseDTO;
import com.content.inventario_stock_servicio.mapper.convert.Convert;
import com.content.inventario_stock_servicio.model.Matter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatterMapper extends Convert<Matter, MatterRequestDTO, MatterResponseDTO> {

    @Override
    MatterResponseDTO toDTO(Matter model);

    @Override
    Matter toModel(MatterRequestDTO dto);
}
