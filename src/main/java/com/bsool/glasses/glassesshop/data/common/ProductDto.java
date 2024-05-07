package com.bsool.glasses.glassesshop.data.common;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private InventoryItemDto inventoryItem;

}
