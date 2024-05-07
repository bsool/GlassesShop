package com.bsool.glasses.glassesshop.data.inventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "inventory_item")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price_USD", nullable = false)
    private float priceUSD;

    @Column(name = "price_GBP", nullable = false)
    private float priceGBP;

    @Column(name = "price_EUR", nullable = false)
    private float priceEUR;

    @Column(name = "price_JOD", nullable = false)
    private float priceJOD;

    @Column(name = "price_JPY", nullable = false)
    private float priceJPY;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inventory_item_type_id", nullable = false)
    private InventoryItemType inventoryItemType;

}