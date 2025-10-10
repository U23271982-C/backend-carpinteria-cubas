package com.content.inventory_matter_service.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "Supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplier_id;

    @Column(name = "supplier_name", nullable = false, length = 11)
    private String supplier_name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @Column(name = "phone_number", nullable = false, length = 9)
    private String phone_number;

    @Column(name = "direction", nullable = false, length = 100)
    private String direction;

    @Column(name = "email", nullable = false, length = 9)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_type_id", nullable = false)
    private SupplierType supplier_type_id;

    //Relations
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> matterSuppliers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private StateEntity state_entity;
}
