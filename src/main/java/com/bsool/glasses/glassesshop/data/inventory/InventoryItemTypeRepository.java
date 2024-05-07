package com.bsool.glasses.glassesshop.data.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemTypeRepository extends JpaRepository<InventoryItemType, Long> {
    Optional<InventoryItemType> findByTypeCode(int typeCode);


}