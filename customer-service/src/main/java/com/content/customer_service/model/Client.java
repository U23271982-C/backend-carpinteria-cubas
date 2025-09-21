package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName", nullable = false, length = 100)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;
    @Column(name = "registrationDate", nullable = false)
    private LocalDateTime registrationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personType_id", nullable = false)
    private PersonType personType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id", nullable = false)
    private Identification identification;

}
