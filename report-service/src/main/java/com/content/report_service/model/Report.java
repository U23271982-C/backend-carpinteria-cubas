package com.content.report_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa los reportes en el sistema.
 * Cada reporte está asociado a un tipo de documento y un estado.
 * Las relaciones están configuradas para carga perezosa.
 */

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
    private Integer report_id;

    @Column(name = "report_name", nullable = false, length = 200)
    private String report_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id", nullable = false)
    private DocType doc_type_id;

    @Column(name = "size_Kb", nullable = false)
    private Double size_Kb;
    @Column(name = "url", nullable = false, length = 255)
    private String url;
    @Column(name = "report_date", nullable = false)
    private LocalDateTime report_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
