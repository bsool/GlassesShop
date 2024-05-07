package com.bsool.glasses.glassesshop.data.product.lens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LensRepository extends JpaRepository<Lens, Long> {
    List<Lens> findByInventoryItem_StockGreaterThan(int stock);
}