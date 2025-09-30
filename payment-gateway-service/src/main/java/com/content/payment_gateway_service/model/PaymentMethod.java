package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los diferentes métodos de pago disponibles en el sistema.
 * Incluye detalles como el nombre del método de pago, una descripción y su estado actual.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "PaymentMethod")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_method_id;

    @Column(name = "payment_method_name", nullable = false,length = 100)
    private String payment_method_name;
    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "payment_method_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttemptPayment> attemptPayments;

}
