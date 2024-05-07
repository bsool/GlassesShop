package com.bsool.glasses.glassesshop.data.product.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
    Optional<CurrencyType> findByTypeNameIgnoreCase(String typeName);
}