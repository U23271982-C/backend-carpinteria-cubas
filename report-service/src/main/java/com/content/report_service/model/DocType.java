package com.content.report_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los tipos de documentos en el sistema.
 * Cada tipo de documento está asociado a un estado (activo, inactivo, eliminado).
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "DocType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doc_type_id;

    @Column(name = "doc_type_name", nullable = false, length = 100)
    private String doc_type_name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "doc_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report> reports;

}
