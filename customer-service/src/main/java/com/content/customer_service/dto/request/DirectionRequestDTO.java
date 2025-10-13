package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de una Dirección.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * La lógica de negocio verificará y creará automáticamente departamento, provincia y distrito si no existen.
 */

@Getter
@Setter
@Builder
public class DirectionRequestDTO {

    // ID del cliente asociado
    @Positive(message = "El ID del cliente debe ser un número positivo")
    @NotNull(message = "El ID del cliente no debe ser nulo")
    private Integer client_id;

    // ID del tipo de dirección (ejemplo: "Casa", "Oficina", "Trabajo")
    @Positive(message = "El ID del tipo de dirección debe ser un número positivo")
    @NotNull(message = "El ID del tipo de dirección no debe ser nulo")
    private Integer direction_type_id;

    // Nombre de la dirección (ejemplo: "Av. Principal", "Calle Los Olivos")
    @Size(max = 50, message = "El nombre de la dirección no debe exceder los 50 caracteres")
    private String direction_name;

    // Número de la dirección (ejemplo: "123", "456-A")
    @Size(max = 50, message = "El número de la dirección no debe exceder los 50 caracteres")
    private String direction_number;

    // Referencia adicional de ubicación
    @Size(max = 255, message = "La referencia no debe exceder los 255 caracteres")
    private String reference;

    // Nombre del departamento (se verificará y creará si no existe)
    @NotBlank(message = "El nombre del departamento no debe estar vacío")
    @Size(max = 100, message = "El nombre del departamento no debe exceder los 100 caracteres")
    private String department_name;

    // Nombre de la provincia (se verificará y creará si no existe)
    @NotBlank(message = "El nombre de la provincia no debe estar vacío")
    @Size(max = 100, message = "El nombre de la provincia no debe exceder los 100 caracteres")
    private String province_name;

    // Nombre del distrito (se verificará y creará si no existe)
    @NotBlank(message = "El nombre del distrito no debe estar vacío")
    @Size(max = 100, message = "El nombre del distrito no debe exceder los 100 caracteres")
    private String district_name;

}
