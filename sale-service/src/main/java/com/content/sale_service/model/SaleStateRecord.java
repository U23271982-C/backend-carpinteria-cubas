package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "SaleStateRecord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleStateRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_state_id", nullable = false)
    private SaleState sale_state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @Column(name = "motive", length = 200)
    private String motive;

    @Column(name = "change_date")
    private LocalDate change_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity;
}
