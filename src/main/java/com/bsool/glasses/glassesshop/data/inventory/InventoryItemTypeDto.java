package com.bsool.glasses.glassesshop.data.inventory;

import com.bsool.glasses.glassesshop.data.common.TypeCode;

import java.io.Serializable;

/**
 * DTO for {@link InventoryItemType}
 */
public class InventoryItemTypeDto extends TypeCode implements Serializable {

    //1 for frame //2 for lens
    public final static int TYPE_CODE_FRAME = 1;
    public final static int TYPE_CODE_LENS = 2;
}