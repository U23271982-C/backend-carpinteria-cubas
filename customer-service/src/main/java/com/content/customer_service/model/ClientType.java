package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="ClientType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String description;
}
