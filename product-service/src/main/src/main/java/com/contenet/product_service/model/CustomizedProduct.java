package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CustomizedProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomizedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "specifications", nullable = false, length = 255)
    private String specifications;
    @Column(name = "estimatedHours", nullable = false)
    private Integer estimatedHours;

}
