package com.bsool.glasses.glassesshop.data.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link InventoryItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemDto implements Serializable {
    private Long id;
    private String description;
    private float priceUSD;
    private float priceGBP;
    private float priceEUR;
    private float priceJOD;
    private float priceJPY;
    private int stock;
    private InventoryItemTypeDto inventoryItemType;
}