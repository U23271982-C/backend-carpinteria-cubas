package com.content.sale_service.mapper;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.mapper.convert.Convert;
import com.content.sale_service.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper
        extends Convert<Sale, SaleRequestDTO, SaleResponseDTO> {
    @Override
    SaleResponseDTO toDTO(Sale modelo);

    /**
     * Ignoramos:<br>
     * id, porque lo pone la bd<br>
     * number_sale, lo agregamos en el servicio<br>
     * date_sale, se agrega en el servicio<br>
     * hour_sale, se agrega en el servicio<br>
     * subtotal, se agrega en el servicio<br>
     * total, se agrega en el servicio<br>
     * @param dto Request DTO
     * @return Model Sale
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number_sale", ignore = true)
    @Mapping(target = "date_sale", ignore = true)
    @Mapping(target = "hour_sale", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "sale_detail", ignore = true)
    @Override
    Sale toModel(SaleRequestDTO dto);
}
