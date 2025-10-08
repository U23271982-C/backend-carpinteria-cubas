package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SupplierType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer supplier_type_id;

    @Column(name = "supplier_name", nullable = false, length = 100)
    private String supplier_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_entity_id", nullable = false)
    private StateEntity state_entity;
}
