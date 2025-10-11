package com.content.authentication_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEmployeeRequestDTO {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "El nombre completo solo puede contener letras y espacios")
    private String fullName;

    @Pattern(regexp = "^\\+?[0-9\\s\\-]{7,15}$", message = "El formato del teléfono no es válido")
    private String phone;

    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres")
    private String address;
}
