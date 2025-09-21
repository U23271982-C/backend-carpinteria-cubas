package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "methodname", nullable = false,length = 100)
    private String methodName;
    @Column(name = "description", length = 255)
    private String description;
}
