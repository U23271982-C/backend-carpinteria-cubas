package com.content.customer_service.model.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * Clase base abstracta para todas las entidades con UUID
 * ACTUALIZADA para ser compatible con Lombok SuperBuilder
 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder // Cambiamos a SuperBuilder para herencia
public abstract class BaseEntity {

    @Column(name = "uuid", nullable = false, unique = true, updatable = false, length = 36)
    private UUID uuid;

    // Constructor sin argumentos requerido por JPA
    protected BaseEntity() {
    }

    /**
     * Genera UUID automáticamente antes de persistir
     */
    @PrePersist
    private void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    /**
     * Valida que el UUID no se modifique
     */
    @PreUpdate
    private void validateUuid() {
        if (this.uuid == null) {
            throw new IllegalStateException("UUID no puede ser null en una entidad existente");
        }
    }
}
