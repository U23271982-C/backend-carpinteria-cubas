package com.content.sale_service.repository;

import com.content.sale_service.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailSaleRepository extends JpaRepository<SaleDetail, Integer> {
}
