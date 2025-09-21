package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="Identification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Identification", nullable = false, length = 50)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identificationType_id", nullable = false)
    private IdentificationType identificationType;
}
