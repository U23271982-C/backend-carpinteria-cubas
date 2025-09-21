package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "departamento", nullable = false, length = 100)
    private String name;

}
