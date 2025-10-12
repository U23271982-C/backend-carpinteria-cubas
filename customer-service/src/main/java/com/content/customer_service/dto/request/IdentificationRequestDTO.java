package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IdentificationRequestDTO {

    // Número de identificación
    @NotBlank(message = "El número de documentación de Identificación no debe estar vacío")
    @Size(max = 12, message = "El número de documentación de Identificación no debe exceder los 12 caracteres")
    private String identification_doc;

    // ID del Tipo de Identificación asociado a Identificación
    @Positive(message = "El ID del Tipo de Identificación debe ser un número positivo")
    @NotNull(message = "El ID del Tipo de Identificación no debe ser nulo")
    private Integer identification_type_id;

}
