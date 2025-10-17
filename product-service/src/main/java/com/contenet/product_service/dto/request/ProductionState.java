package com.contenet.product_service.dto.request;

import com.contenet.product_service.model.StateEntity;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Getter
@Setter
public class ProductionState {

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String production_state_name;

}
