package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "employee_id", nullable = false)
    private Integer id_Employee;
    @Column(name = "address_type_id", nullable = false)
    private Integer id_Address_Type;
    @Column(name = "name_via", nullable = false, length = 100)
    private String name_Via;
    @Column(name = "number", nullable = false, length = 10)
    private String number;
    @Column(name = "reference", nullable = true, length = 255)
    private String reference;
    @Column(name = "distric_id", nullable = false)
    private Integer id_Distric;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
