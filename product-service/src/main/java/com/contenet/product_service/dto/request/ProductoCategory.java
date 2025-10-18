package com.contenet.product_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class ProductoCategory {

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    @Size(max = 255, message = "El nombre de la categoría no puede exceder los 255 caracteres.")
    private String categoryName;
}
