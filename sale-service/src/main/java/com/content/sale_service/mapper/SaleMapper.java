package com.content.sale_service.mapper;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.mapper.convert.Convert;
import com.content.sale_service.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper
        extends Convert<Sale, SaleRequestDTO, SaleResponseDTO> {
    @Override
    SaleResponseDTO toDTO(Sale modelo);

    @Override
    Sale toModel(SaleRequestDTO dto);
}
