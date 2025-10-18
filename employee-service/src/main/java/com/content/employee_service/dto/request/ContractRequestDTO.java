package com.content.employee_service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import com.content.employee_service.utility.ValidateGroup;
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
    @Size(min = 1, max = 100, message = "El nombre del contrato debe tener entre 1 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "El nombre del contrato no puede estar vacío", groups = ValidateGroup.OnCreate.class)
    private String contract_name;

    // Fecha de inicio del contrato
    @FutureOrPresent(message = "La fecha de inicio del contrato debe ser hoy o una fecha futura", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotNull(message = "La fecha de inicio no puede ser nula", groups = ValidateGroup.OnCreate.class)
    private LocalDateTime start_date;

    // Fecha de finalización del contrato
    @Future(message = "La fecha de finalización del contrato debe ser una fecha futura", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotNull(message = "La fecha de finalización no puede ser nula", groups = ValidateGroup.OnCreate.class)
    private LocalDateTime end_date;

    // Tipo de contrato (ID)
    @NotNull(message = "El UUID del tipo de contrato es obligatorio", groups = ValidateGroup.OnCreate.class)
    private UUID contract_type_uuid;

    // Salario del contrato
    @Positive(message = "El salario debe ser un número positivo", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotNull(message = "El salario no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    @Digits(integer = 10, fraction = 2,message = "El salario no puede tener más de 10 dígitos", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    private double salary;

    private UUID state_entity_uuid;
}
