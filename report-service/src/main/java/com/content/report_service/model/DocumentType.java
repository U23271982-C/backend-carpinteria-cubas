package com.content.report_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ClientSession")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "typeName", nullable = false, length = 100)
    private String typeName;

    @Column(name = "description", length = 255)
    private String description;

}
