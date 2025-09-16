package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Enitdad que representa el estado de una entidad
 * 0: ELIMINADO
 * 1: ACTIVO
 * 2: INACTIVO
 */
@Entity
@Table(name = "StateEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private int id;

    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sale;

    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetail> saleDetails;

    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleState> saleStates;

    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleStateRecord> saleStateRecords;
}
