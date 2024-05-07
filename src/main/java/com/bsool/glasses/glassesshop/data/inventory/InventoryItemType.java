package com.bsool.glasses.glassesshop.data.inventory;

import com.bsool.glasses.glassesshop.data.common.TypeCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "inventory_item_type")
public class InventoryItemType extends TypeCode {

    public static final int TYPE_CODE_FRAME = 1;
    public static final int TYPE_CODE_LENS = 2;
}