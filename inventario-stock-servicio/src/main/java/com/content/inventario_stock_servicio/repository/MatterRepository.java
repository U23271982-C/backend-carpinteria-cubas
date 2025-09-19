package com.content.inventario_stock_servicio.repository;
import com.content.inventario_stock_servicio.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatterRepository extends JpaRepository<Matter, Integer> {
}
