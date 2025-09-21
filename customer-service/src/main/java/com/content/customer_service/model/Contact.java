package com.content.customer_service.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name ="Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "telephone", nullable = false, length = 20)
    private String telephone;
    @Column(name = "email", nullable = false, length = 100)
    private String email;



}
