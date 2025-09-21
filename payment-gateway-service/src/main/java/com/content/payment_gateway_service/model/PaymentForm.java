package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "PaymentForm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fortname",nullable = false, length = 100)
    private String fortName;
    @Column(name = "description", length = 255)
    private String description;
}
