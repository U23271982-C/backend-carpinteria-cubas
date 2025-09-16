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
    private Double subtotal;
    @Column()

    @OneToMany(mappedBy = "sale_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SaleDetail> saleDetail;
}
