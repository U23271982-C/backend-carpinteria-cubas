package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de una Dirección.
 */

@Getter
@Setter
@Builder
public class DirectionRequestDTO {

    // ID del Cliente asociado
    @Positive(message = "El ID del Cliente debe ser un número positivo")
    @NotNull(message = "El ID del Cliente no debe ser nulo")
    private Integer client_id;

    // ID del Tipo de Dirección asociado
    @Positive(message = "El ID del Tipo de Dirección debe ser un número positivo")
    @NotNull(message = "El ID del Tipo de Dirección no debe ser nulo")
    private Integer direction_type_id;

    // Nombre de la dirección
    @Size(max = 50, message = "El nombre de la dirección no debe exceder los 50 caracteres")
    private String direction_name;

    // Número de la dirección
    @Size(max = 50, message = "El número de la dirección no debe exceder los 50 caracteres")
    private String direction_number;

    // Referencia
    @Size(max = 255, message = "La referencia no debe exceder los 255 caracteres")
    private String reference;

    // ID del Distrito asociado
    @Positive(message = "El ID del Distrito debe ser un número positivo")
    @NotNull(message = "El ID del Distrito no debe ser nulo")
    private Integer district_id;

}
