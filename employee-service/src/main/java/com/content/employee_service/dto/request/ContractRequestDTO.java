package com.content.employee_service.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object para la entidad Contract.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class ContractRequestDTO {

    // Nombre del contrato
    @Size(min = 1, max = 100, message = "El nombre del contrato debe tener entre 1 y 100 caracteres")
    @NotBlank(message = "El nombre del contrato no puede estar vacío")
    private String contract_name;

    // Fecha de inicio del contrato
    @FutureOrPresent(message = "La fecha de inicio del contrato debe ser hoy o una fecha futura")
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDateTime start_date;

    // Fecha de finalización del contrato
    @Future(message = "La fecha de finalización del contrato debe ser una fecha futura")
    @NotNull(message = "La fecha de finalización no puede ser nula")
    private LocalDateTime end_date;

    // Tipo de contrato (ID)
    @Positive(message = "El ID del tipo de contrato debe ser un número positivo")
    @NotNull(message = "El ID tipo de contrato no puede ser nulo")
    private Integer contract_type_id;

    // Salario del contrato
    @Positive(message = "El salario debe ser un número positivo")
    @NotNull(message = "El salario no puede ser nulo")
    @Size(max = 10, message = "El salario no puede tener más de 10 dígitos")
    private double salary;

}
