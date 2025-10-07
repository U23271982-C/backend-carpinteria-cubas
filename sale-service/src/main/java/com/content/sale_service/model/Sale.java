package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    /**
     * Codigo de la venta, para identificar la venta
     */
    @Column(name = "number_sale", length = 20)
    private String number_sale;
    @Column(name = "date_sale")
    private LocalDate date_sale;
    @Column(name = "hour_sale")
    private LocalTime hour_sale;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "total")
    private double total;

    /**
     * categoria de la venta (prefabricado/personalizado)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_category_id", nullable = false)
    private SaleCategory sale_category;

    /**
     * Fecha estimada de entrega de la venta
     */
    @Column(name = "sale_delivery_date")
    private LocalDate sale_delivery_date;

    /**
     * Monto del anticipo de la venta
     */
    @Column(name = "sale_start_payment")
    private BigDecimal sale_start_payment;

    /**
     * Monto del pago final de la venta
     */
    @Column(name = "sale_final_payment")
    private BigDecimal sale_final_payment;

    /**
     * id_cliente del microservicio customer-service
     */
    @Column(name = "client_id")
    private int client_id;

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
