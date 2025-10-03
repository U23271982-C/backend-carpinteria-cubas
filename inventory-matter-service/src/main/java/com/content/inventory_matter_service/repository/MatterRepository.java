package com.content.inventory_matter_service.repository;
import com.content.inventory_matter_service.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatterRepository extends JpaRepository<Matter, Integer> {
}
