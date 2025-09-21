package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ProductCategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "productcategory",nullable = false,length = 100)
    private String categoryName;
}
