package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_sale")
    private Integer number_sale;
    @Column(name = "date_sale")
    private LocalDate date_sale;
    @Column(name = "hour_sale")
    private LocalTime hour_sale;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "total")
    private double total;

    // id_cliente (traemos datos de una api o que)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_state_id", nullable = false)
    private SaleState sale_state;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SaleDetail> sale_detail;

    @OneToMany(mappedBy = "sale")
    private List<SaleStateRecord> sale_state_record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity;
}
