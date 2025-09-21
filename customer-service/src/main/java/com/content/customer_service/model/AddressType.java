package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="AddressType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddressType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
