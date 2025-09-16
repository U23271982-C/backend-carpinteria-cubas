package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SaleState")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_state", nullable = false, length = 100)
    private String name_state;
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @OneToMany(mappedBy = "saleState", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sales;

    @OneToMany(mappedBy = "saleState", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleStateRecord> saleStateRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity stateEntity;
}
