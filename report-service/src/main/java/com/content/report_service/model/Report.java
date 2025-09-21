package com.content.report_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_reporte", nullable = false, length = 150)
    private String reportName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_doc", nullable = false)
    private DocumentType documentType;

    @Column(name = "peso", nullable = false)
    private Double sizeKb;
    @Column(name = "url", nullable = false, length = 255)
    private String url;
    @Column(name = "fecha_reporte", nullable = false)
    private LocalDateTime reportDate;

}
