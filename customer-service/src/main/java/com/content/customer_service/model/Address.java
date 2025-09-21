package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="Address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressType_id", nullable = false)
    private AddressType addressType;
    @Column(name = "streetNumber", length = 50)
    private String streetNumber;
    @Column(name = "referencia", length = 255)
    private String reference;
    @Column(name = "detail", length = 255)
    private String detail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
}
