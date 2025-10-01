package com.content.sale_service.mapper;

import com.content.sale_service.common.Formatting;
import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.mapper.convert.Convert;
import com.content.sale_service.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SaleMapper
        extends Convert<Sale, SaleRequestDTO, SaleResponseDTO> {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

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
    @Mapping(target = "number_sale", expression = "java(SaleMapper.generateSaleNumber(dto.getClient_id()))") // Se ejecuta y retorna el numero de venta ya generado
    @Mapping(target = "date_sale", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "hour_sale", expression = "java(java.time.LocalTime.now())")
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "sale_detail", ignore = true)
    @Override
    Sale toModel(SaleRequestDTO dto);

    /**
     * Genera un número único para la venta
     */
    static String generateSaleNumber(int clientId) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern(Formatting.DATE_STANDARD));
        String timeComponent = LocalTime.now().format(DateTimeFormatter.ofPattern(Formatting.TIME_STANDARD));
        return String.format("S-%d-%s-%s", clientId, timestamp, timeComponent); //S-12-20250920-190912
    }
}
