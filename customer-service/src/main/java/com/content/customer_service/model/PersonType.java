package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="PersonType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PersonType", nullable = false, length = 100)
    private String type;
}
