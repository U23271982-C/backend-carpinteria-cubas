package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de una Provincia.
 */

@Getter
@Setter
@Builder
public class ProvinceRequestDTO {

    // Nombre de la provincia
    @NotBlank(message = "El nombre de la provincia no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de la provincia debe tener entre 3 y 50 caracteres")
    private String province_name;

    // ID del Departamento asociado
    @Positive(message = "El ID del Departamento debe ser un número positivo")
    @NotNull(message = "El ID del Departamento no debe ser nulo")
    private Integer department_id;

}
