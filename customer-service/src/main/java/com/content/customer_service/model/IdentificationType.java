package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="IdentificationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IdentificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IdentificationType", nullable = false, length = 100)
    private String type;
}
