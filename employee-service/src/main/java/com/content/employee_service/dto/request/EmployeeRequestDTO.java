package com.content.employee_service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import com.content.employee_service.utility.ValidateGroup;
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
    @Size(min = 1, max = 100, message = "El nombre del empleado debe tener entre 2 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "El nombre del empleado es obligatorio", groups = ValidateGroup.OnCreate.class)
    private String employee_name;

    // Apellidos del empleado
    @Size(min = 1, max = 100, message = "Los apellidos del empleado debe tener entre 2 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "Los apellidos del empleado es obligatorio", groups = ValidateGroup.OnCreate.class)
    private String employee_last_name;

    // Fecha de nacimiento del empleado
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotNull(message = "La fecha de nacimiento no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private LocalDateTime birth_date;

    // Fecha de registro del empleado
    @NotBlank(message = "El DNI es obligatorio", groups = ValidateGroup.OnCreate.class)
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @Pattern(regexp = "\\d{8}", message = "El DNI debe contener solo números", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    private String identification_number;

    // ID del tipo de identificación del empleado
    @NotNull(message = "El UUID del tipo de identificación no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID identification_type_uuid;

    // ID del cargo del empleado
    @NotNull(message = "El ID del cargo no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID post_id_uuid;

    // ID del contrato del empleado
    @NotNull(message = "El ID del contrato no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID contract_id_uuid;

    // ID del distrito del empleado
    @NotNull(message = "El ID del distrito no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID distric_id_uuid;

    // Dirección de email del empleado
    @Email(message = "La dirección de email debe ser válida", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "La dirección de email es obligatoria", groups = ValidateGroup.OnCreate.class)
    private String direction_email;

    private UUID state_entity_uuid;
}
