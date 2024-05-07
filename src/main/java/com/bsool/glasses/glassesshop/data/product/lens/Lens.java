package com.bsool.glasses.glassesshop.data.product.lens;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "lens")
public class Lens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "inventory_item_id", nullable = false, unique = true)
    private InventoryItem inventoryItem;

    @Column(name = "colour", nullable = false)
    private String colour;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lens_type_code", nullable = false)
    private LensType lensType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prescription_type_id", nullable = false)
    private PrescriptionType prescriptionType;

}