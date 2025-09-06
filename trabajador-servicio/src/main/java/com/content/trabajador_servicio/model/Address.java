package com.content.trabajador_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Address {
    private int id;
    private int id_Employee;
    private int id_Address_Type;
    private String name_Via;
    private String number;
    private String reference;
    private int id_Distric;
}
