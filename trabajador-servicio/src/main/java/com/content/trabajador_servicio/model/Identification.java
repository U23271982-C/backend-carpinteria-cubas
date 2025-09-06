package com.content.trabajador_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Identification {
    private int id;
    private String name_Identification;
    private int id_Identification_Type;
}
