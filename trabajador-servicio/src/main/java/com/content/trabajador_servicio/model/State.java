package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "State")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private int id;
    @Column(name = "state", nullable = false, length = 100)
    private String name_State;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Position> position;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PersonType> personType;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IdentificationType> identificationType;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Identification> identification;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employee;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Distric> distric;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContractType> contractType;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contract> contract;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contact;
}
