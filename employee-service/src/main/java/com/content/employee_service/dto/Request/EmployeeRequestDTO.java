package com.content.employee_service.dto.Request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object para la entidad Employee.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class EmployeeRequestDTO {

    // Nombre del empleado
    @Size(min = 1, max = 100, message = "El nombre del empleado debe tener entre 2 y 100 caracteres")
    @NotBlank(message = "El nombre del empleado es obligatorio")
    private String employee_name;

    // Apellidos del empleado
    @Size(min = 1, max = 100, message = "Los apellidos del empleado debe tener entre 2 y 100 caracteres")
    @NotBlank(message = "Los apellidos del empleado es obligatorio")
    private String employee_last_name;

    // Fecha de nacimiento del empleado
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @NotNull(message = "La fecha de nacimiento no puede ser nulo")
    private LocalDateTime birth_date;

    // Fecha de registro del empleado
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe contener solo números")
    private String identification_number;

    // ID del tipo de identificación del empleado
    @Positive(message = "El ID del tipo de identificación debe ser un número positivo")
    @NotNull(message = "El ID del tipo de identificación no puede ser nulo")
    private Integer identification_type_id;

    // ID del cargo del empleado
    @NotNull(message = "El ID del cargo no puede ser nulo")
    @Positive(message = "El ID del cargo debe ser un número positivo")
    private Integer post_id;

    // ID del contrato del empleado
    @NotNull(message = "El ID del contrato no puede ser nulo")
    @Positive(message = "El ID del contrato debe ser un número positivo")
    private Integer contract_id;

    // ID del distrito del empleado
    @NotNull(message = "El ID del distrito no puede ser nulo")
    @Positive(message = "El ID del distrito debe ser un número positivo")
    private Integer distric_id;

    // Dirección de email del empleado
    @Email(message = "La dirección de email debe ser válida")
    @NotBlank(message = "La dirección de email es obligatoria")
    private String direction_email;

}
