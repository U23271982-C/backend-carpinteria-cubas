package com.content.trabajador_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Province {
    private int id;
    private String name_Province;
    private int id_Department;
}
