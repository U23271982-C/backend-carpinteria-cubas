package com.contenet.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StateEntityDTO {

    @NotBlank(message = "El nombre del estado no puede estar vacío.")
    @Size(max = 100, message = "El nombre del estado no debe exceder los 100 caracteres.")
    private String state_entity_name;
}